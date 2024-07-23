package com.samin.jobserver.service;

import com.samin.jobsdk.SystemConstant;
import com.samin.jobsdk.bean.JobDto;
import com.samin.jobserver.entity.Job;
import com.samin.jobserver.entity.JobLog;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * 核心逻辑
 * <p>
 * Description: 核心逻辑
 * <p>
 * Created By: Samin
 * <p>
 * Created Date: 2024-03-24
 */
@Slf4j
@Service
@EnableScheduling
@RequiredArgsConstructor
public class JobGenerateService {

    private final JobService jobService;
    private final RabbitTemplate rabbitTemplate;

    /**
     * 执行定时任务，精度为 5 秒一次
     */
    @Scheduled(cron = "0/5 * * * * *")
    public void processJob() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        log.info("process job {}", dtf.format(now));

        List<Job> list = jobService.getList(now);

        // TODO 多线程处理循环
        for (Job job : list) {
            log.info("process job {} {} {}", job.getName(), job.getActionCode(), job.getParamJson());

            JobLog jobLog = jobService.generateJobLog(job, now);

            // 推送任务
            JobDto dto = new JobDto();
            dto.setLogId(jobLog.getId());
            dto.setProcessTime(now);
            dto.setActionCode(job.getActionCode());
            dto.setParamJson(job.getParamJson());
            rabbitTemplate.convertAndSend(SystemConstant.TOPIC_EXCHANGE_NAME, job.getAppCode() + SystemConstant.JOB_ROUTING_KEY, dto);
        }
    }
}
