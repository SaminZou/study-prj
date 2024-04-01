package com.samin.jobserver.service;

import com.samin.jobsdk.SystemConstant;
import com.samin.jobsdk.bean.JobCallbackDto;
import com.samin.jobserver.entity.JobLog;
import com.samin.jobserver.repository.JobLogRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class JobCallbackService {

    private final JobLogRepository jobLogRepository;

    @RabbitListener(queues = SystemConstant.JOB_CALLBACK_QUEUE_NAME)
    public void onMessage(JobCallbackDto jobCallbackDto) {
        log.info("定时任务：日志 ID：{},执行结果：{},完成时间：{}", jobCallbackDto.getLogId(), jobCallbackDto.getResult(), jobCallbackDto.getEndTime());

        Optional<JobLog> logOpt = jobLogRepository.findById(jobCallbackDto.getLogId());
        if (logOpt.isPresent()) {
            JobLog jobLog = logOpt.get();
            jobLog.setResult(jobCallbackDto.getResult());
            jobLog.setErrorMsg(jobCallbackDto.getErrorMsg());
            jobLog.setStartTime(jobCallbackDto.getStartTime());
            jobLog.setEndTime(jobCallbackDto.getEndTime());
            jobLog.setUpdateTime(LocalDateTime.now());
            jobLogRepository.save(jobLog);
        }
    }
}
