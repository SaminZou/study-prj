package com.samin.mq.service;

import com.samin.mq.config.RabbitmqConfig;
import java.util.concurrent.TimeUnit;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
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
    private final Receiver receiver;

    public Producer(Receiver receiver, RabbitTemplate rabbitTemplate) {
        this.receiver = receiver;
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Sending message...");

        rabbitTemplate.convertAndSend(RabbitmqConfig.topicExchangeName, "foo.bar.baz", "Hello from RabbitMQ!");

        receiver.getLatch().await(10000, TimeUnit.MILLISECONDS);
    }
}
