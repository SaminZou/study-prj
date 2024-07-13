package com.samin.zookeeperdemo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SnowflakeIdService {

    @Autowired
    private SnowflakeIdWorker snowflakeIdWorker;

    /**
     * 开始时间 2024-01-01 00:00:00
     */
    private static final long START_TIME = 1704038400000L;

    /**
     * 最多支持 8192 个节点
     */
    private final static int WORKER_ID_BITS = 13;

    /**
     * 序列号，支持单节点最高每毫秒的最大 ID 数 1024
     */
    private final static int SEQUENCE_BITS = 10;

    /**
     * 最大的 worker id，8091
     * -1 的补码（二进制全 1）右移 13 位，然后取反
     */
    private final static long MAX_WORKER_ID = ~(-1L << WORKER_ID_BITS);

    /**
     * 最大的序列号，1023
     * -1 的补码（二进制全 1）右移 10 位，然后取反
     */
    private final static long MAX_SEQUENCE = ~(-1L << SEQUENCE_BITS);

    /**
     * worker 节点编号的移位
     */
    private final static long WORKER_ID_SHIFT = SEQUENCE_BITS;

    /**
     * 时间戳的移位
     */
    private final static long TIMESTAMP_LEFT_SHIFT = WORKER_ID_BITS + SEQUENCE_BITS;

    /**
     * 该项目的 worker 节点 id
     */
    private long workerId;

    /**
     * 上次生成 ID 的时间戳
     */
    private long lastTimestamp = -1L;

    /**
     * 当前毫秒生成的序列
     */
    private long sequence = 0L;

    public void init(long workerId) {
        if (workerId > MAX_WORKER_ID) {
            // zk分配的workerId过大
            throw new IllegalArgumentException("worker Id wrong: " + workerId);
        }
        this.workerId = workerId;
    }

    public Long nextId() {
        return generateId();
    }

    private synchronized long generateId() {
        long current = System.currentTimeMillis();

        // 检测是否出现时钟回拨问题
        if (current < lastTimestamp) {
            return -1;
        }

        if (current == lastTimestamp) {
            // 如果当前生成 id 的时间还是上次的时间，那么对序列号 +1
            sequence = (sequence + 1) & MAX_SEQUENCE;

            if (sequence == MAX_SEQUENCE) {
                // 当前毫秒生成的序列数已经大于最大值，那么阻塞到下一个毫秒再获取最新的时间戳
                current = this.nextMs(lastTimestamp);
            }
        } else {
            // 当前的时间戳已经是下一个毫秒
            sequence = 0L;
        }

        // 更新上次生成 id 的时间戳
        lastTimestamp = current;

        // 进行移位操作生成 int64 的唯一ID
        // 时间戳右移 23 位
        long time = (current - START_TIME) << TIMESTAMP_LEFT_SHIFT;

        // workerId 右移 10 位
        long workerId = this.workerId << WORKER_ID_SHIFT;

        return time | workerId | sequence;
    }

    private long nextMs(long timestamp) {
        long current = System.currentTimeMillis();
        while (current <= timestamp) {
            current = System.currentTimeMillis();
        }
        return current;
    }
}