package com.samin.aop.config;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Slf4j
@Aspect
@Component
public class AopConfig {

    /**
     * 定义一个切入点
     */
    @Pointcut("execution(* com.samin.aop.controller.*.test(..))")
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
