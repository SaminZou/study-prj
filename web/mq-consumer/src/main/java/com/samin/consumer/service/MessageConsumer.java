package com.samin.consumer.service;

import com.samin.common.SystemConstant;
import com.samin.common.entity.MessageBody;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class MessageConsumer {

    @RabbitListener(queues = SystemConstant.msgConsumerQueueName)
    public void onMessage(MessageBody message) {
        System.out.println("Message <" + message + ">");
    }
}
