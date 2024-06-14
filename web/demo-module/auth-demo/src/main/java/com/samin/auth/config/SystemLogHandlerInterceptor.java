package com.samin.auth.config;

import cn.hutool.core.util.StrUtil;
import com.samin.auth.authentication.CustomUserDetails;
import com.samin.auth.common.Log;
import com.samin.auth.entity.SystemLog;
import com.samin.auth.repo.SystemLogRepository;
import com.samin.auth.service.SecurityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Objects;

@Slf4j
@Component
public class SystemLogHandlerInterceptor implements HandlerInterceptor {

    private final SystemLogRepository systemLogRepository;
    private final SecurityService securityService;

    public SystemLogHandlerInterceptor(SystemLogRepository systemLogRepository, SecurityService securityService) {
        this.systemLogRepository = systemLogRepository;
        this.securityService = securityService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        LocalDateTime now = LocalDateTime.now();
        RequestThreadLocal.Request requestData = RequestThreadLocal.getRequest();
        requestData.setRequestTime(now);
        requestData.setRequestStartTime(now.atZone(ZoneId.systemDefault())
                .toInstant()
                .toEpochMilli());
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            Log anno = handlerMethod.getMethodAnnotation(Log.class);

            // 记录请求日志
            if (Objects.nonNull(anno)) {
                RequestThreadLocal.Request requestData = RequestThreadLocal.getRequest();

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

                SystemLog instance = SystemLog.getInstance(request, response, requestData, user, anno.value(),
                        endTime - requestData.getRequestStartTime());
                systemLogRepository.save(instance);

                log.info("Response: {} {} - Status: {}", request.getMethod(), request.getRequestURI(), response.getStatus());

                // 防止内存溢出
                RequestThreadLocal.clear();
            }
        }
    }
}
