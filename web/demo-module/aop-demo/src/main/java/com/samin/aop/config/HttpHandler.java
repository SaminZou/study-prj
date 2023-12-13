package com.samin.aop.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 实践证明一个请求接口，同时配置了 HandlerInterceptor 和 Aspect 的情况下，会优先被 HandlerInterceptor 拦截
 * <p>
 * Description:
 * <p>
 * Created By: Samin
 * <p>
 * Created Date: 2023-12-12
 */
@Slf4j
public class HttpHandler implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("进入拦截器 HandlerInterceptor");
        return true;
    }
}
