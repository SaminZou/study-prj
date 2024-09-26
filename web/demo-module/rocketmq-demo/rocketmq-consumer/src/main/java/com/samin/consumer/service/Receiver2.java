package com.samin.consumer.service;

import com.samin.common.entity.MessageBody;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

/**
 * 消费者
 * <p>
 * Description: 消费者
 * <p>
 * Created By: Samin
 * <p>
 * Created Date: 2024-09-20
 */
@Component
@RocketMQMessageListener(topic = "test-topic2", consumerGroup = "consumer-group2")
public class Receiver2 implements RocketMQListener<MessageBody> {

    @Override
    public void onMessage(MessageBody message) {
        System.out.println("接收到消息: " + message);
        // 处理消息的业务逻辑
    }
}
