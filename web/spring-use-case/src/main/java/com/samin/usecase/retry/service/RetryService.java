package com.samin.usecase.retry.service;

import java.time.LocalTime;
import lombok.extern.slf4j.Slf4j;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

/**
 * 重试服务类
 *
 * <p>Description: 重试服务类，需要 @EnableRetry
 *
 * <p>Created By: Samin
 *
 * <p>Created Date: 2024-02-01
 */
@Slf4j
@Service
@EnableRetry
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
    public int retry(int code, int isRetry) throws Exception {
        isRetry = isRetry > 0 ? 3 : isRetry;
        log.info("业务方法被调用，时间：" + LocalTime.now());
        if (index < isRetry) {
            index += 1;
            throw new Exception("业务方法被调用，执行报错！");
        }
        log.info("业务方法调用成功");
        return code;
    }

    @Recover
    public int recover(Exception e, int code) {
        log.info("业务方法调用失败，触发执行对应的方法回调！！！！");
        // 记日志到数据库，或者进行补救措施
        return -1;
    }
}
