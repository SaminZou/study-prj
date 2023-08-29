package com.samin.auth.config;

import cn.hutool.core.util.StrUtil;
import com.samin.auth.authentication.CustomUserDetails;
import com.samin.auth.common.Log;
import com.samin.auth.entity.SystemLog;
import com.samin.auth.repo.SystemLogRepository;
import com.samin.auth.service.SecurityService;
import java.time.Instant;
import java.util.Objects;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@Component
public class SystemLogFilter implements HandlerInterceptor {

    private final ThreadLocal<Long> requestStartTimeThreadLocal = new ThreadLocal<>();

    private final SystemLogRepository systemLogRepository;
    private final SecurityService securityService;

    public SystemLogFilter(SystemLogRepository systemLogRepository, SecurityService securityService) {
        this.systemLogRepository = systemLogRepository;
        this.securityService = securityService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        requestStartTimeThreadLocal.set(Instant.now()
                                               .toEpochMilli());
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            Log anno = handlerMethod.getMethodAnnotation(Log.class);

            // 记录请求日志
            if (Objects.nonNull(anno)) {
                long startTime = requestStartTimeThreadLocal.get();

                log.info("Request: {} {} from {}", request.getMethod(), request.getRequestURI(), request.getRemoteAddr());

                long endTime = Instant.now()
                                      .toEpochMilli();

                CustomUserDetails user;
                // 适配没有登录过的连接日志记录
                if (StrUtil.equals(request.getRequestURI(), "/login")) {
                    user = null;
                } else {
                    user = securityService.getCurrentUser();
                }

                systemLogRepository.save(SystemLog.getInstance(request, response, user, anno.value(), endTime - startTime));

                log.info("Response: {} {} - Status: {}", request.getMethod(), request.getRequestURI(), response.getStatus());

                // 防止内存溢出
                requestStartTimeThreadLocal.remove();
            }
        }
    }
}
