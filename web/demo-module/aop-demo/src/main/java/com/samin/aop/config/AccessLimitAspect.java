package com.samin.aop.config;

import com.samin.aop.annotation.AccessLimit;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.TimeUnit;


/**
 * 模拟滑动窗口控制
 * <p>
 * Description: 模拟滑动窗口控制
 * <p>
 * Created By: Samin
 * <p>
 * Created Date: 2023-11-29
 */
@Slf4j
@Aspect
@Component
public class AccessLimitAspect {

    /**
     * 统计请求的处理时间
     */
    private final ConcurrentMap<String, AccessCounter> counterMap = new ConcurrentHashMap<>();


    @Before("@annotation(accessLimit)")
    public void doBefore(JoinPoint joinPoint, AccessLimit accessLimit) throws Throwable {
        // 获取 HttpServletRequest 对象
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();

        // 唯一的 key
        String accessKey = request.getRequestURI();

        // 获取滑动窗口的时间间隔和最大访问次数
        int seconds = accessLimit.seconds();
        int maxCount = accessLimit.maxCount();

        // 获取或创建访问计数器
        AccessCounter counter = counterMap.computeIfAbsent(accessKey, key -> new AccessCounter(seconds));

        // 判断当前访问是否超过限制
        if (!counter.tryAcquire(maxCount)) {
            log.error(accessKey + " 访问频率过高，请稍后再试！");
            throw new Exception("访问频率过高，请稍后再试！");
        }
    }

    private static class AccessCounter {

        private int index = 0;
        private long currentSecond;
        private final int seconds;

        public AccessCounter(int seconds) {
            this.seconds = seconds;
            this.currentSecond = this.currentTimeSecond();
        }

        public boolean tryAcquire(int limit) {
            synchronized (this) {
                // 如果当前时间与前一个时间戳间隔超过秒数限制，则重置计数器
                if (this.currentTimeSecond() - currentSecond > seconds) {
                    this.index = 1;
                    this.currentSecond = this.currentTimeSecond();
                    return true;
                }

                // 判断是否超过访问次数限制
                if (this.index >= limit) {
                    return false;
                } else {
                    this.index += 1;
                    return true;
                }
            }
        }

        private long currentTimeSecond() {
            return TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis());
        }
    }
}
