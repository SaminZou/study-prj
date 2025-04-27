package com.samin.usecase.async;

import java.lang.reflect.Method;
import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * Spring Boot 异步功能配置
 *
 * <p>Description: 默认线程池配置，@EnableAsync 和 @Configuration 放在一起方便管理
 *
 * <p>Created By: Samin Created Date: 2023-08-24
 */
@EnableAsync
@Configuration
@Slf4j
public class AsyncConfig implements AsyncConfigurer {

    @Override
    public Executor getAsyncExecutor() {
        // 此类由 Spring 提供，org.springframework.scheduling.concurrent 包下，是线程池的封装类
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        // 线程池中线程的名字前缀
        taskExecutor.setThreadNamePrefix("taskThreadPool-async-");
        // 线程池核心线程数量
        taskExecutor.setCorePoolSize(5);
        // 线程池最大线程数量
        taskExecutor.setMaxPoolSize(10);
        // 线程池空闲线程存活时间，单位秒
        taskExecutor.setKeepAliveSeconds(100);
        // 线程池拒绝策略
        taskExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.DiscardOldestPolicy());
        // 线程池任务队容量，如果不设置则默认 Integer.MAX_VALUE，
        // 队列默认使用 LinkedBlockingQueue 若 queueCapacity 的值 <= 0,则使用 SynchronousQueue
        taskExecutor.setQueueCapacity(1000);

        // 线程池中核心线程是否允许超时，默认为 false
        taskExecutor.setAllowCoreThreadTimeOut(true);

        // 线程池中的超时处理时间，单位秒，有一个对应方法为毫秒，默认为不超时
        taskExecutor.setAwaitTerminationSeconds(60);

        // 初始化线程池，不可以少，否者会抛出 线程池没有初始化
        taskExecutor.initialize();
        return taskExecutor;
    }

    /**
     * 线程未处理异常的统一处理机制，即线程池异常处理器,简单示例
     *
     * @return
     */
    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        // 异常处理器函数接口类
        return (throwable, method, objects) -> {
            log.error("============ " + throwable.getMessage() + " ===========", throwable);
            log.error("============ " + method.getName() + " ===========", objects);
        };
    }
}
