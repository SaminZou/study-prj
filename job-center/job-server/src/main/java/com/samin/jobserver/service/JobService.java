package com.samin.jobserver.service;

import com.samin.jobserver.entity.Job;
import com.samin.jobserver.entity.JobLog;
import com.samin.jobserver.repository.JobLogRepository;
import com.samin.jobserver.repository.JobRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.support.CronExpression;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class JobService {

    private final JobRepository jobRepository;
    private final JobLogRepository jobLogRepository;

    public List<Job> getList(LocalDateTime now) {
        // TODO 需要过滤有配置 Job Worker 的定时任务
        return jobRepository.findByProcessTimeBeforeAndIsDeleteAndIsEnable(now, 0, 1);
    }

    @Transactional
    public JobLog generateJobLog(Job job, LocalDateTime now) {
        // 任务下次执行时间
        CronExpression exp = CronExpression.parse(job.getCron());
        LocalDateTime next = exp.next(now);
        job.setProcessTime(next);
        jobRepository.save(job);

        // 生成日志
        JobLog log = new JobLog();
        log.setJobId(job.getId());
        log.setResult(false);
        jobLogRepository.save(log);

        return log;
    }
}
