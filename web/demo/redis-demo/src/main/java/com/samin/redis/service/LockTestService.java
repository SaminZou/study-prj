package com.samin.redis.service;

import java.util.concurrent.TimeUnit;
import lombok.RequiredArgsConstructor;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Service;

/**
 * Redisson 实现分布式锁
 *
 * @author samin
 * @date 2022-12-30
 */
@Service
@RequiredArgsConstructor
public class LockTestService {

    private final RedissonClient redissonClient;

    public void testRedissonLock() throws InterruptedException {
        // 获取锁
        RLock lock = redissonClient.getLock("lock");
        // 获取锁 参数：获取锁的最大等待时间(期间会重试)，锁自动释放时间，时间单位
        boolean isLock = lock.tryLock(1, 10, TimeUnit.SECONDS);
        if (isLock) {
            try {
                // 业务逻辑
                System.out.println("获取成功");
                Thread.sleep(5000);
            } finally {
                // 释放锁
                lock.unlock();
            }
        }
    }
}
