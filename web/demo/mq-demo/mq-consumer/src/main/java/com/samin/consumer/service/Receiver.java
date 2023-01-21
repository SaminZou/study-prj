package com.samin.consumer.service;

import com.samin.common.SystemConstant;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * 消费者
 *
 * @author samin
 * @date 2022-12-25
 */
@Component
public class Receiver {

    @RabbitListener(queues = SystemConstant.queueName)
    public void onMessage(String message) {
        System.out.println("Received <" + message + ">");
    }
}
