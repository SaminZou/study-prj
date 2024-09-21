package com.samin.producer.service;

import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * 生产者
 * <p>
 * Description: 生产者
 * <p>
 * Created By: Samin
 * <p>
 * Created Date: 2024-09-20
 */
@Component
public class Producer implements CommandLineRunner {

    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    @Override
    public void run(String... args) throws Exception {
        rocketMQTemplate.convertAndSend("test-topic", "test message");
        System.out.println("消息发送成功: " + "test message");
    }
}