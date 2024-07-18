package com.samin.idempotent.config;

import com.samin.idempotent.exception.BusException;
import com.samin.idempotent.itf.RequestLock;
import com.samin.idempotent.service.RequestKeyGenerator;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

import java.lang.reflect.Method;

/**
 * 使用分布式锁实现
 * <p>
 * Description: 常用是使用单点 Redis，使用 StringRedisTemplate 编写业务逻辑
 * <p>
 * Created By: Samin
 * <p>
 * Created Date: 2024-07-18
 */
@Aspect
@Configuration
@RequiredArgsConstructor
public class RedissonRequestLockAspect {

    private final RedissonClient redissonClient;

    @Around("execution(public * * (..)) && @annotation(com.samin.idempotent.itf.RequestLock)")
    public Object interceptor(ProceedingJoinPoint joinPoint) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        RequestLock requestLock = method.getAnnotation(RequestLock.class);
        if (StringUtils.isEmpty(requestLock.prefix())) {
            throw new BusException(50000, "重复提交前缀不能为空");
        }
        //获取自定义 key
        final String lockKey = RequestKeyGenerator.getLockKey(joinPoint);
        // 使用 Redisson 分布式锁的方式判断是否重复提交
        RLock lock = redissonClient.getLock(lockKey);
        boolean isLocked = false;
        try {
            // 尝试抢占锁
            isLocked = lock.tryLock();
            // 没有拿到锁说明已经有了请求了
            if (!isLocked) {
                throw new BusException(50001, "您的操作太快了,请稍后重试");
            }
            // 拿到锁后设置过期时间
            lock.lock(requestLock.expire(), requestLock.timeUnit());

            try {
                return joinPoint.proceed();
            } catch (Throwable throwable) {
                throw new BusException(50002, "系统异常");
            }
        } catch (Exception e) {
            throw new BusException(50003, "您的操作太快了,请稍后重试");
        } finally {
            //释放锁
            if (isLocked && lock.isHeldByCurrentThread()) {
                lock.unlock();
            }
        }
    }
}