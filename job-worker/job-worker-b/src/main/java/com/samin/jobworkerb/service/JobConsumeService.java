package com.samin.jobworkerb.service;

import com.samin.jobsdk.SystemConstant;
import com.samin.jobsdk.bean.JobCallbackDto;
import com.samin.jobsdk.bean.JobDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Slf4j
@Service
@RequiredArgsConstructor
public class JobConsumeService {

    private final RabbitTemplate rabbitTemplate;

    @RabbitListener(queues = SystemConstant.JOB_QUEUE_NAME)
    public void onMessage(JobDto job) {
        log.info("定时任务：日志 ID：{},执行码：{},参数：{},执行时间：{}", job.getLogId(), job.getActionCode(), job.getParamJson(), job.getProcessTime());

        JobCallbackDto jobCallbackDto = new JobCallbackDto();
        jobCallbackDto.setLogId(job.getLogId());
        jobCallbackDto.setResult(true);
        jobCallbackDto.setStartTime(LocalDateTime.now());

        try {
            // TODO process business
        } catch (Exception e) {
            jobCallbackDto.setResult(false);
            jobCallbackDto.setErrorMsg(e.getMessage());
        }

        jobCallbackDto.setEndTime(LocalDateTime.now());
        rabbitTemplate.convertAndSend(SystemConstant.TOPIC_EXCHANGE_NAME, SystemConstant.JOB_ROUTING_KEY, jobCallbackDto);
    }
}
