package com.samin.jobcenter.service;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

/**
 * 核心逻辑
 * <p>
 * Description: 核心逻辑
 * <p>
 * Created By: Samin
 * <p>
 * Created Date: 2024-03-24
 */
@Service
@EnableScheduling
public class JobGenerateService {

    /**
     * 执行定时任务，精度为 5 秒一次
     */
    @Scheduled(cron = "0/5 * * * * *")
    public void processJob() {

    }
}
