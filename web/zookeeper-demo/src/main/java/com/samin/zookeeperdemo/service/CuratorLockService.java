package com.samin.zookeeperdemo.service;

import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class CuratorLockService {

    @Autowired
    private OrderCodeGenerator orderCodeGenerator;
    @Autowired
    private InterProcessMutex lock;

    @Async
    public void generator() {
        try {
            // 加锁
            lock.acquire();

            String orderCode = orderCodeGenerator.getOrderCode();
            System.out.println("生成订单号 " + orderCode);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                // 释放锁
                lock.release();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}