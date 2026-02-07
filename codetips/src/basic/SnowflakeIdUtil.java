package basic;

/**
 * 雪花算法生成唯一ID工具类
 * 
 * 雪花算法是Twitter开源的分布式ID生成算法，生成的ID具有以下特点：
 * - 64位长整型数字
 * - 趋势递增（毫秒级时间戳在高位）
 * - 分布式环境下唯一
 * - 不依赖数据库等第三方系统
 * 
 * 标准雪花算法ID结构（64位）：
 * 0 - 0000000000 0000000000 0000000000 0000000000 0 - 00000 - 00000 - 000000000000
 * 1位符号位 | 41位时间戳 | 5位数据中心ID | 5位机器ID | 12位序列号
 * 
 * @author samin
 * @date 2022-01-04
 * @version 2.0
 */
public class SnowflakeIdUtil {
    
    // ============================ 常量定义 ============================
    
    /** 起始时间戳 (2010-11-04 09:42:54) */
    private final long twepoch = 1288834974657L;
    
    /** 机器ID所占的位数 */
    private final long workerIdBits = 5L;
    
    /** 数据中心ID所占的位数 */
    private final long datacenterIdBits = 5L;
    
    /** 序列号所占的位数 */
    private final long sequenceBits = 12L;
    
    /** 机器ID向左移12位 */
    private final long workerIdShift = sequenceBits;
    
    /** 数据中心ID向左移17位(12+5) */
    private final long datacenterIdShift = sequenceBits + workerIdBits;
    
    /** 时间戳向左移22位(5+5+12) */
    private final long timestampLeftShift = sequenceBits + workerIdBits + datacenterIdBits;
    
    /** 生成序列的掩码，这里为4095 (0b111111111111=0xfff=4095) */
    private final long sequenceMask = ~(-1L << sequenceBits);
    
    /** 支持的最大机器ID，结果是31 */
    private final long maxWorkerId = ~(-1L << workerIdBits);
    
    /** 支持的最大数据中心ID，结果是31 */
    private final long datacenterIdMask = ~(-1L << datacenterIdBits);
    
    // ============================ 成员变量 ============================
    
    /** 工作机器ID(0~31) */
    private final long workerId;
    
    /** 数据中心ID(0~31) */
    private final long datacenterId;
    
    /** 毫秒内序列(0~4095) */
    private long sequence = 0L;
    
    /** 上次生成ID的时间戳 */
    private long lastTimestamp = -1L;
    
    // ============================ 构造方法 ============================
    
    /**
     * 构造函数
     * 
     * @param workerId 工作ID (0~31)
     * @param datacenterId 数据中心ID (0~31)
     */
    public SnowflakeIdUtil(long workerId, long datacenterId) {
        this(workerId, datacenterId, 0L);
    }
    
    /**
     * 构造函数
     * 
     * @param workerId 工作ID (0~31)
     * @param datacenterId 数据中心ID (0~31)
     * @param sequence 初始序列号
     */
    public SnowflakeIdUtil(long workerId, long datacenterId, long sequence) {
        // 参数校验
        if (workerId > maxWorkerId || workerId < 0) {
            throw new IllegalArgumentException(
                String.format("worker Id can't be greater than %d or less than 0", maxWorkerId));
        }
        if (datacenterId > datacenterIdMask || datacenterId < 0) {
            throw new IllegalArgumentException(
                String.format("datacenter Id can't be greater than %d or less than 0", datacenterIdMask));
        }
        
        this.workerId = workerId;
        this.datacenterId = datacenterId;
        this.sequence = sequence;
        
        System.out.printf("SnowflakeIdUtil initialized. WorkerId: %d, DatacenterId: %d, Sequence: %d%n", 
                         workerId, datacenterId, sequence);
    }
    
    // ============================ 核心方法 ============================
    
    /**
     * 获得下一个ID (线程安全)
     * 
     * @return SnowflakeId
     */
    public synchronized long nextId() {
        long timestamp = timeGen();
        
        // 如果当前时间小于上一次ID生成的时间戳，说明系统时钟回退过，抛出异常
        if (timestamp < lastTimestamp) {
            System.err.printf("Clock moved backwards. Refusing to generate id for %d milliseconds%n", 
                            lastTimestamp - timestamp);
            throw new RuntimeException(
                String.format("Clock moved backwards. Refusing to generate id for %d milliseconds",
                            lastTimestamp - timestamp));
        }
        
        // 如果是同一时间生成的，则进行毫秒内序列
        if (lastTimestamp == timestamp) {
            sequence = (sequence + 1) & sequenceMask;
            // 毫秒内序列溢出
            if (sequence == 0) {
                // 阻塞到下一个毫秒，获得新的时间戳
                timestamp = tilNextMillis(lastTimestamp);
            }
        } else {
            // 时间戳改变，毫秒内序列重置
            sequence = 0L;
        }
        
        // 上次生成ID的时间戳
        lastTimestamp = timestamp;
        
        // 移位并通过或运算拼到一起组成64位的ID
        return ((timestamp - twepoch) << timestampLeftShift)
                | (datacenterId << datacenterIdShift)
                | (workerId << workerIdShift)
                | sequence;
    }
    
    /**
     * 解析雪花ID的各个组成部分
     * 
     * @param id 雪花ID
     * @return 解析结果字符串
     */
    public static String parseId(long id) {
        long timestamp = (id >> 22) + 1288834974657L;
        long datacenterId = (id >> 17) & 0x1F; // 5位
        long workerId = (id >> 12) & 0x1F;     // 5位
        long sequence = id & 0xFFF;            // 12位
        
        return String.format(
            "ID解析结果: 时间戳=%d (%s), 数据中心ID=%d, 机器ID=%d, 序列号=%d",
            timestamp, new java.util.Date(timestamp), datacenterId, workerId, sequence);
    }
    
    /**
     * 获取ID生成的时间戳
     * 
     * @param id 雪花ID
     * @return 时间戳
     */
    public static long getTimestamp(long id) {
        return (id >> 22) + 1288834974657L;
    }
    
    /**
     * 获取ID生成的数据中心ID
     * 
     * @param id 雪花ID
     * @return 数据中心ID
     */
    public static long getDatacenterId(long id) {
        return (id >> 17) & 0x1F;
    }
    
    /**
     * 获取ID生成的机器ID
     * 
     * @param id 雪花ID
     * @return 机器ID
     */
    public static long getWorkerId(long id) {
        return (id >> 12) & 0x1F;
    }
    
    /**
     * 获取ID生成的序列号
     * 
     * @param id 雪花ID
     * @return 序列号
     */
    public static long getSequence(long id) {
        return id & 0xFFF;
    }
    
    // ============================ 辅助方法 ============================
    
    /**
     * 阻塞到下一个毫秒，直到获得新的时间戳
     * 
     * @param lastTimestamp 上次生成ID的时间戳
     * @return 当前时间戳
     */
    private long tilNextMillis(long lastTimestamp) {
        long timestamp = timeGen();
        while (timestamp <= lastTimestamp) {
            timestamp = timeGen();
        }
        return timestamp;
    }
    
    /**
     * 返回当前时间（毫秒）
     * 
     * @return 当前时间(毫秒)
     */
    private long timeGen() {
        return System.currentTimeMillis();
    }
    
    // ============================ 测试用例 ============================
    
    /**
     * 测试方法 - 演示各种使用场景
     */
    public static void main(String[] args) {
        System.out.println("=== 雪花算法ID生成器测试 ===\n");
        
        // 1. 基础使用示例
        demonstrateBasicUsage();
        
        // 2. 多线程并发测试
        demonstrateConcurrentUsage();
        
        // 3. 不同机器配置测试
        demonstrateDifferentConfigurations();
        
        // 4. ID解析功能测试
        demonstrateIdParsing();
        
        // 5. 性能测试
        demonstratePerformance();
    }
    
    /**
     * 基础使用示例
     */
    private static void demonstrateBasicUsage() {
        System.out.println("1. 基础使用示例");
        System.out.println("-".repeat(50));
        
        SnowflakeIdUtil idGenerator = new SnowflakeIdUtil(1, 1);
        
        // 生成10个ID
        for (int i = 0; i < 10; i++) {
            long id = idGenerator.nextId();
            System.out.printf("ID %d: %d%n", i + 1, id);
        }
        System.out.println();
    }
    
    /**
     * 多线程并发测试
     */
    private static void demonstrateConcurrentUsage() {
        System.out.println("2. 多线程并发测试");
        System.out.println("-".repeat(50));
        
        final SnowflakeIdUtil idGenerator = new SnowflakeIdUtil(2, 2);
        
        // 创建5个线程并发生成ID
        Thread[] threads = new Thread[5];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < 3; j++) {
                    long id = idGenerator.nextId();
                    System.out.printf("线程[%s] 生成ID: %d%n", 
                                    Thread.currentThread().getName(), id);
                }
            });
            threads[i].start();
        }
        
        // 等待所有线程完成
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println();
    }
    
    /**
     * 不同机器配置测试
     */
    private static void demonstrateDifferentConfigurations() {
        System.out.println("3. 不同机器配置测试");
        System.out.println("-".repeat(50));
        
        // 不同数据中心和机器的ID生成器
        SnowflakeIdUtil[] generators = {
            new SnowflakeIdUtil(1, 1), // 数据中心1，机器1
            new SnowflakeIdUtil(2, 1), // 数据中心1，机器2
            new SnowflakeIdUtil(1, 2), // 数据中心2，机器1
            new SnowflakeIdUtil(2, 2)  // 数据中心2，机器2
        };
        
        for (int i = 0; i < generators.length; i++) {
            long id = generators[i].nextId();
            System.out.printf("配置[数据中心=%d, 机器=%d] 生成ID: %d%n",
                            generators[i].getDatacenterId(id),
                            generators[i].getWorkerId(id), id);
        }
        System.out.println();
    }
    
    /**
     * ID解析功能测试
     */
    private static void demonstrateIdParsing() {
        System.out.println("4. ID解析功能测试");
        System.out.println("-".repeat(50));
        
        SnowflakeIdUtil idGenerator = new SnowflakeIdUtil(3, 3);
        long id = idGenerator.nextId();
        
        System.out.println("生成的ID: " + id);
        System.out.println(parseId(id));
        System.out.println();
    }
    
    /**
     * 性能测试
     */
    private static void demonstratePerformance() {
        System.out.println("5. 性能测试");
        System.out.println("-".repeat(50));
        
        SnowflakeIdUtil idGenerator = new SnowflakeIdUtil(4, 4);
        
        int count = 10000;
        long startTime = System.currentTimeMillis();
        
        for (int i = 0; i < count; i++) {
            idGenerator.nextId();
        }
        
        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;
        
        System.out.printf("生成 %d 个ID耗时: %d 毫秒%n", count, duration);
        System.out.printf("平均每个ID生成时间: %.3f 微秒%n", (duration * 1000.0) / count);
        System.out.printf("QPS: %.0f%n", (count * 1000.0) / duration);
        System.out.println();
    }
    
    // ============================ Getter方法 ============================
    
    /**
     * 获取工作机器ID
     */
    public long getWorkerId() {
        return workerId;
    }
    
    /**
     * 获取数据中心ID
     */
    public long getDatacenterId() {
        return datacenterId;
    }
    
    /**
     * 获取序列号
     */
    public long getSequence() {
        return sequence;
    }
}
