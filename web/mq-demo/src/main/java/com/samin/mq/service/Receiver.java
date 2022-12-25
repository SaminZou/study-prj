package com.samin.mq.service;

import java.util.concurrent.CountDownLatch;
import org.springframework.stereotype.Component;

/**
 * 消费者
 *
 * @author samin
 * @date 2022-12-25
 */
@Component
public class Receiver {

    private CountDownLatch latch = new CountDownLatch(1);

    public void receiveMessage(String message) {
        System.out.println("Received <" + message + ">");
        latch.countDown();
    }

    public CountDownLatch getLatch() {
        return latch;
    }
}
