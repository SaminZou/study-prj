package basic;

/**
 * 雪花算法生成唯一 ID
 *
 * @author samin
 * @date 2022-01-04
 */
public class SnowflakeIdUtil {

    /**
     * <p> 优点：
     * <p> - 毫秒数在高位，自增序列在低位，整个 ID 都是趋势递增的
     * <p> - 不依赖数据库等第三方系统，以服务的方式部署，稳定性更高，生成 ID 的性能也是非常高的
     * <p> - 可以根据自身业务特性分配 bit 位，非常灵活
     *
     * <p> 缺点：
     * <p> - 强依赖机器时钟，如果机器上时钟回拨，会导致发号重复或者服务会处于不可用状态
     *
     * @param args
     */
    public static void main(String[] args) {
        SnowflakeIdUtil idWorkerUtil = new SnowflakeIdUtil(1, 1, 0L);
        System.out.println(idWorkerUtil.nextId());
        System.out.println(idWorkerUtil.nextId());
        System.out.println(idWorkerUtil.nextId());
        System.out.println(idWorkerUtil.nextId());
    }

    private final long workerId;
    private final long datacenterId;
    private long sequence;

    public SnowflakeIdUtil(long workerId, long datacenterId, long sequence) {
        // sanity check for workerId
        long maxWorkerId = ~(-1L << workerIdBits);
        if (workerId > maxWorkerId || workerId < 0) {
            throw new IllegalArgumentException(
                    String.format("worker Id can't be greater than %d or less than 0", maxWorkerId));
        }
        long maxDatacenterId = ~(-1L << datacenterIdBits);
        if (datacenterId > maxDatacenterId || datacenterId < 0) {
            throw new IllegalArgumentException(
                    String.format("datacenter Id can't be greater than %d or less than 0", maxDatacenterId));
        }
        System.out.printf(
                "worker starting. timestamp left shift %d, datacenter id bits %d, worker id bits %d, sequence bits %d, workerid %d",
                timestampLeftShift, datacenterIdBits, workerIdBits, sequenceBits, workerId);

        this.workerId = workerId;
        this.datacenterId = datacenterId;
        this.sequence = sequence;
    }

    private final long workerIdBits = 5L;
    private final long datacenterIdBits = 5L;
    private final long sequenceBits = 12L;

    private final long timestampLeftShift = sequenceBits + workerIdBits + datacenterIdBits;

    private long lastTimestamp = -1L;

    public synchronized long nextId() {
        long timestamp = timeGen();

        if (timestamp < lastTimestamp) {
            System.err.printf("clock is moving backwards.  Rejecting requests until %d.", lastTimestamp);
            throw new RuntimeException(String.format("Clock moved backwards.  Refusing to generate id for %d milliseconds",
                                                     lastTimestamp - timestamp));
        }

        if (lastTimestamp == timestamp) {
            long sequenceMask = ~(-1L << sequenceBits);
            sequence = (sequence + 1) & sequenceMask;
            if (sequence == 0) {
                timestamp = tilNextMillis(lastTimestamp);
            }
        } else {
            sequence = 0;
        }

        lastTimestamp = timestamp;
        long twepoch = 1288834974657L;
        long datacenterIdShift = sequenceBits + workerIdBits;
        return ((timestamp - twepoch) << timestampLeftShift) | (datacenterId << datacenterIdShift) | (workerId
                << sequenceBits) | sequence;
    }

    private long tilNextMillis(long lastTimestamp) {
        long timestamp = timeGen();
        while (timestamp <= lastTimestamp) {
            timestamp = timeGen();
        }
        return timestamp;
    }

    private long timeGen() {
        return System.currentTimeMillis();
    }
}
