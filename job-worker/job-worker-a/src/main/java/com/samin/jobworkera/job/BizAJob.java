package com.samin.jobworkera.job;

import com.samin.jobsdk.exception.JobException;
import com.samin.jobsdk.itf.JobWorker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component("biz1Job")
public class BizAJob implements JobWorker {

    @Override
    public String action() throws JobException {
        log.info("biz1Job is working...");
        throw new JobException("biz 执行失败");
    }
}
