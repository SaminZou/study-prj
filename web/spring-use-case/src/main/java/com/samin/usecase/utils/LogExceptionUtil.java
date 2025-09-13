package com.samin.usecase.utils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LogExceptionUtil {

    public static void error(String message, Object context, Exception e) {
        log.error("{} | 上下文: {} | 错误: {}", message, context, e.getMessage(), e);
    }
}
