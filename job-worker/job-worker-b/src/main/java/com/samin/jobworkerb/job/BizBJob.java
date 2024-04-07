package com.samin.jobworkerb.job;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.samin.jobsdk.exception.JobException;
import com.samin.jobsdk.itf.JobWorker;
import com.samin.jobworkerb.bean.ParamBo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component("biz2Job")
public class BizBJob implements JobWorker {

    private final ObjectMapper objectMapper;

    @Override
    public String action(String param) throws JobException {
        log.info("biz2Job is working...");

        try {
            // parse param object
            ParamBo paramBo = objectMapper.readValue(param, ParamBo.class);
            log.info("定时任务参数为：{}", paramBo);

            Thread.sleep(2000);
        } catch (JsonMappingException e) {
            throw new JobException(e.getMessage());
        } catch (InterruptedException | JsonProcessingException e) {
            throw new JobException(e.getMessage());
        }
        return null;
    }
}
