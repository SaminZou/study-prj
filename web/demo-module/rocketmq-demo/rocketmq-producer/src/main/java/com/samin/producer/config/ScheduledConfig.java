package com.samin.producer.config;

import com.samin.producer.service.Producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableScheduling
public class ScheduledConfig {

    @Autowired
    private Producer producer;

    @Scheduled(fixedRate = 5000)
    public void sendMessage() {
        producer.run();
    }
}