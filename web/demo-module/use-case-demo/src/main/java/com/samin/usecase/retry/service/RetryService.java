package com.samin.usecase.retry.service;

import java.time.LocalTime;
import lombok.extern.slf4j.Slf4j;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class RetryService {

    private int index = 0;

    /**
     * 注意 maxAttempts 的次数是包含了所有的执行次数
     *
     * @param code
     * @return
     * @throws Exception
     */
    @Retryable(value = Exception.class, maxAttempts = 2, backoff = @Backoff(delay = 2000, multiplier = 1.5))
    public int retry(int code) throws Exception {
        // param > 1 就可以观测 recover() 方法被执行
        int param = 1;
        log.info("业务方法被调用，时间：" + LocalTime.now());
        if (index < param) {
            index += 1;
            throw new Exception("业务方法被调用，执行报错！");
        }
        log.info("业务方法调用成功");
        return code;
    }

    @Recover
    public int recover(Exception e, int code) {
        log.info("业务方法调用失败，触发执行对应的方法回调！！！！");
        //记日志到数据库，或者进行补救措施
        return -1;
    }
}
