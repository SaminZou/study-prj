package com.samin.usecase.scheduler.config;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@EnableScheduling
public class TestScheduler {

    /**
     * 每隔 15 秒运行一次
     */
    @Scheduled(cron = "0/15 * * * * *")
    public void test() {
        log.info("每隔 15 秒运行一次: {}", LocalDateTime.now()
                                                        .format(DateTimeFormatter.ofPattern("HH:mm:ss")));
    }

    /**
     * 每分钟的 05 秒运行一次
     */
    @Scheduled(cron = "5 * * * * ?")
    public void test2() {
        log.info("now: {}", LocalDateTime.now()
                                         .format(DateTimeFormatter.ofPattern("HH:mm:ss")));
    }
}