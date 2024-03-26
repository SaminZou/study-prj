package com.samin.jobserver.service;

import com.samin.jobsdk.bean.JobDTO;
import com.samin.jobserver.entity.Job;
import com.samin.jobserver.entity.JobLog;
import com.samin.jobserver.repository.JobLogRepository;
import com.samin.jobserver.repository.JobRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.support.CronExpression;
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

    private final JobRepository jobRepository;
    private final JobLogRepository jobLogRepository;
    private final RabbitTemplate rabbitTemplate;

    /**
     * 执行定时任务，精度为 5 秒一次
     */
    @Scheduled(cron = "0/5 * * * * *")
    public void processJob() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        log.info("process job {}", dtf.format(now));

        List<Job> list = jobRepository.findByProcessTimeAfter(now);
        for (Job job : list) {
            log.info("process job {} {} {}", job.getName(), job.getActionCode(), job.getParamJson());

            // 任务下次执行时间
            // TODO 需要事务控制
            CronExpression exp = CronExpression.parse(job.getCron());
            LocalDateTime next = exp.next(now);
            job.setProcessTime(next);
            jobRepository.save(job);

            // 生成日志
            JobLog log = new JobLog();
            log.setJobId(job.getId());
            log.setResult(false);
            jobLogRepository.save(log);

            // 推送任务
            JobDTO dto = new JobDTO();
            dto.setLogId(log.getId());
            dto.setProcessTime(now);
            dto.setActionCode(job.getActionCode());
            dto.setParamJson(job.getParamJson());
            rabbitTemplate.convertAndSend("job-exchange", job.getName(), dto);
        }
    }
}
