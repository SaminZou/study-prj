package com.samin.auth.config;

import java.time.Instant;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * API 接口认证
 * <p>
 * Description: 使用 appkey 和 appsecret 进行 API 接口认证
 * <p>
 * Created By: Samin
 * <p>
 * Created Date: 2024-03-18
 */
@Slf4j
@Aspect
@Component
public class AopConfig {

    /**
     * 定义一个切入点
     */
    @Pointcut("execution(* com.samin.auth.controller.ApiController.*(..))")
    public void pc1() {

    }

    /**
     * 前置通知
     *
     * @param jp
     */
    @Before(value = "pc1()")
    public void before(JoinPoint jp) {
        String name = jp.getSignature()
                        .getName();
        Object[] args = jp.getArgs();
        log.info(name + "方法开始执行...");
        log.info("方法参数: " + args[0]);
    }

    /**
     * 后置通知
     *
     * @param jp
     */
    @After(value = "pc1()")
    public void after(JoinPoint jp) {
        String name = jp.getSignature()
                        .getName();
        log.info(name + "方法执行结束...");
    }

    /**
     * 返回通知
     *
     * @param jp
     * @param result
     */
    @AfterReturning(value = "pc1()", returning = "result")
    public void afterReturning(JoinPoint jp, Object result) {
        String name = jp.getSignature()
                        .getName();
        log.info(name + "方法返回值为：" + result);
    }

    /**
     * 异常通知
     *
     * @param jp
     * @param e
     */
    @AfterThrowing(value = "pc1()", throwing = "e")
    public void afterThrowing(JoinPoint jp, Exception e) {
        String name = jp.getSignature()
                        .getName();
        log.info(name + "方法抛异常了，异常是：" + e.getMessage());
    }

    /**
     * 环绕通知
     *
     * @param pjp
     * @return
     * @throws Throwable
     */
    @Around("pc1()")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        String name = pjp.getSignature()
                         .getName();
        // 统计方法执行时间
        long start = Instant.now()
                            .toEpochMilli();
        Object result = pjp.proceed();
        long end = Instant.now()
                          .toEpochMilli();
        log.info(name + "方法执行时间为：" + (end - start) + " ms");
        return result;
    }
}
