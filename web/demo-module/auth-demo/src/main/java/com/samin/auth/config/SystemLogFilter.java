package com.samin.auth.config;

import cn.hutool.core.util.StrUtil;
import com.samin.auth.authentication.CustomUserDetails;
import com.samin.auth.entity.SystemLog;
import com.samin.auth.repo.SystemLogRepository;
import com.samin.auth.service.SecurityService;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@Component
@RequiredArgsConstructor
public class SystemLogFilter implements HandlerInterceptor {

    private final SystemLogRepository systemLogRepository;
    private final SecurityService securityService;

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
        List<String> ignoreUrls = new ArrayList<>();
        ignoreUrls.add("/doc.html");
        ignoreUrls.add("/doc.html/**");
        ignoreUrls.add("/webjars/**");
        ignoreUrls.add("/v2/**");
        ignoreUrls.add("/swagger-resources");
        ignoreUrls.add("/swagger-resources/**");
        ignoreUrls.add("/swagger-ui.html");
        ignoreUrls.add("/swagger-ui.html/**");
        if (ignoreUrls.contains(request.getRequestURI())) {
            return;
        }

        long startTime = Instant.now()
                                .toEpochMilli();

        // 记录请求日志
        log.info("Request: {} {} from {}", request.getMethod(), request.getRequestURI(), request.getRemoteAddr());

        // 执行请求处理链
        // chain.doFilter(request, response);

        long endTime = Instant.now()
                              .toEpochMilli();

        CustomUserDetails user;
        if (StrUtil.equals(request.getRequestURI(), "/login")) {
            user = null;
        } else {
            user = securityService.getCurrentUser();
        }

        // TODO 执行时间是错误的，使用了 spring mvc HandlerInterceptor 需要重新调整算法
        systemLogRepository.save(SystemLog.getInstance(request, response, user, endTime - startTime));
        // 记录响应日志
        log.info("Response: {} {} - Status: {}", request.getMethod(), request.getRequestURI(), response.getStatus());
    }
}
