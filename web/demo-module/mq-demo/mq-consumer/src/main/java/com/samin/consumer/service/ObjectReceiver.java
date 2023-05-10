package com.samin.consumer.service;

import com.samin.common.SystemConstant;
import com.samin.common.entity.MessageBody;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class ObjectReceiver {

    @RabbitListener(queues = SystemConstant.MSG_CONSUMER_QUEUE_NAME)
    public void onMessage(MessageBody message) {
        System.out.println("Message <" + message + ">");
    }
}
