package com.samin.jobworkera.service;

import com.samin.jobsdk.SystemConstant;
import com.samin.jobsdk.bean.JobDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class JobConsumeService {

    @RabbitListener(queues = SystemConstant.QUEUE_NAME)
    public void onMessage(JobDto job) {
        log.info("定时任务：{},{},{},{}", job.getLogId(), job.getActionCode(), job.getParamJson(), job.getProcessTime());
    }
}
