package com.samin.usecase.retry.service;

import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

import java.time.LocalTime;

@Service
public class RetryService {

    private int index = 0;

    @Retryable(value = Exception.class, maxAttempts = 3, backoff = @Backoff(delay = 2000, multiplier = 1.5))
    public int test(int code) throws Exception {
        System.out.println("test被调用,时间：" + LocalTime.now());
        if (index < 5) {
            index += 1;
            throw new Exception("情况不对头！");
        }
        System.out.println("test被调用,情况对头了！");
        return 200;
    }

    @Recover
    public int recover(Exception e, int code) {
        System.out.println("回调方法执行！！！！");
        //记日志到数据库 或者调用其余的方法
        return 400;
    }
}
