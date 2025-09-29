package com.samin.usecase.scheduler;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class TestScheduler {

    private final ScheduledExecutorService retryScheduler = Executors.newSingleThreadScheduledExecutor();

    private int retryCount = 0;
    // minutes
    private final List<Long> retryDelays = Arrays.asList(1L, 15L, 30L);

    @Scheduled(cron = "0 0 10 * * ?")
    public void fetchDataTask() {
        fetchDataAndRetryIfNeeded();
    }

    private void fetchDataAndRetryIfNeeded() {
        if (retryCount < retryDelays.size()) {
            long delay = retryDelays.get(retryCount);
            retryCount++;
            System.out.printf("返回空数据，%d 分钟后重试（第 %d 次）...\n", delay, retryCount);

            retryScheduler.schedule(this::fetchDataAndRetryIfNeeded, delay, TimeUnit.MINUTES);
        } else {
            System.out.println("已重试三次仍无数据，终止重试。");
        }
    }


    /**
     * 每隔 15 秒运行一次
     */
    @Scheduled(cron = "0/15 * * * * *")
    public void test0() {
        log.info("每隔 15 秒运行一次: {}", LocalDateTime.now()
                                                        .format(DateTimeFormatter.ofPattern("HH:mm:ss")));
    }

    /**
     * 方法 2：每隔 15 秒运行一次（系统启动后会立即执行一次任务，然后每隔 15 秒执行一次）
     */
    @Scheduled(fixedRate = 15 * 1000)
    public void test1() {
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