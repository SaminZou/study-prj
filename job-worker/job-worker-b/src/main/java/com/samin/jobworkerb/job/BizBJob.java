package com.samin.jobworkerb.job;

import com.samin.jobsdk.exception.JobException;
import com.samin.jobsdk.itf.JobWorker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component("biz2Job")
public class BizBJob implements JobWorker {

    @Override
    public String action() throws JobException {
        log.info("biz2Job is working...");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
