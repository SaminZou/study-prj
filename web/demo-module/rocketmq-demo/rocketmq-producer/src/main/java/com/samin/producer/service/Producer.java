package com.samin.producer.service;

import com.samin.common.entity.MessageBody;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
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
public class Producer {

    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    public void run() {
        MessageBody messageBody = MessageBody.builder()
                                             .msgId(1L)
                                             .text("foo")
                                             .content("bar")
                                             .userId(1L)
                                             .build();
        rocketMQTemplate.convertAndSend("test-topic2", messageBody);
        rocketMQTemplate.convertAndSend("test-topic", "test message");
        System.out.println("消息发送成功: " + "test message");
    }
}