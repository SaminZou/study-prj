package com.samin.producer.service;

import com.samin.common.SystemConstant;
import com.samin.common.entity.MessageBody;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * 生产者
 *
 * @author samin
 * @date 2022-12-25
 */
@Component
public class Producer implements CommandLineRunner {

    private final RabbitTemplate rabbitTemplate;

    public Producer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Sending message...");

        // 发送字符串消息
        sendStringMsg();
        // 发送对象消息，使用 Jackson
        sendObjectMsg();
        // 循环发送消息
        sendLoopMsg();
    }

    public void sendStringMsg() {
        rabbitTemplate.convertAndSend(SystemConstant.TOPIC_EXCHANGE_NAME, "foo.bar.baz", "Hello from RabbitMQ!");
    }

    public void sendObjectMsg() {
        MessageBody messageBody = MessageBody.builder()
                                             .msgId(1L)
                                             .text("foo")
                                             .content("bar")
                                             .userId(1L)
                                             .build();
        rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());
        rabbitTemplate.convertAndSend(SystemConstant.TOPIC_EXCHANGE_NAME, "msg.foo", messageBody);
    }

    public void sendLoopMsg() throws InterruptedException {
        int count = 1;
        while (true) {
            Thread.sleep(1000L);

            rabbitTemplate.convertAndSend(SystemConstant.TOPIC_EXCHANGE_NAME, "work.bar", "[Loop] msg: " + count++);
        }
    }
}