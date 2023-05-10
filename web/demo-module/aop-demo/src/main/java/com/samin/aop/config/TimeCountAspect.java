package com.samin.aop.config;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@Aspect
@Component
public class TimeCountAspect {

    /**
     * 统计请求的处理时间
     */
    ThreadLocal<Long> startTime = new ThreadLocal<>();

    /**
     * 带有 @TimeCount 注解的方法
     */
    @Pointcut("@annotation(com.samin.aop.annotation.TimeCount)")
    public void timeCount() {

    }

    @Before("timeCount()")
    public void doBefore(JoinPoint joinPoint) throws Throwable {
        startTime.set(System.currentTimeMillis());
        // 接收到请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        // 记录请求的内容
        log.info("请求URL: {}", request.getRequestURL().toString());
        log.info("请求METHOD: {}", request.getMethod());
    }

    @AfterReturning(pointcut = "timeCount()")
    public void doAfterReturning() {
        // 处理完请求后，返回内容
        log.info("方法执行时间: {}", (System.currentTimeMillis() - startTime.get()));
        startTime.remove();
    }
}
