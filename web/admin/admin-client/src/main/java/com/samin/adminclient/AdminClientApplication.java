package com.samin.adminclient;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@EnableScheduling
@SpringBootApplication
public class AdminClientApplication {

    private final Logger logger = LoggerFactory.getLogger(AdminClientApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(AdminClientApplication.class, args);
    }

    @Scheduled(cron = "0/5 * * * * ?")
    private void triggerTask() {
        logger.info("trigger task");
    }
}
