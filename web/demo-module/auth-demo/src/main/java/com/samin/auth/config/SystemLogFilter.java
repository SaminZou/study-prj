package com.samin.auth.config;

import cn.hutool.core.util.StrUtil;
import com.samin.auth.authentication.CustomUserDetails;
import com.samin.auth.entity.SystemLog;
import com.samin.auth.repo.SystemLogRepository;
import com.samin.auth.service.SecurityService;
import java.io.IOException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class SystemLogFilter implements Filter {

    private final SystemLogRepository systemLogRepository;
    private final SecurityService securityService;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        List<String> ignoreUrls = new ArrayList<>();
        ignoreUrls.add("/doc.html");
        ignoreUrls.add("/doc.html/**");
        ignoreUrls.add("/webjars/**");
        ignoreUrls.add("/v2/**");
        ignoreUrls.add("/swagger-resources");
        ignoreUrls.add("/swagger-resources/**");
        ignoreUrls.add("/swagger-ui.html");
        ignoreUrls.add("/swagger-ui.html/**");
        if (ignoreUrls.contains(httpRequest.getRequestURI())) {
            return;
        }

        long startTime = Instant.now()
                                .toEpochMilli();

        // 记录请求日志
        log.info("Request: {} {} from {}", httpRequest.getMethod(), httpRequest.getRequestURI(),
                 httpRequest.getRemoteAddr());

        // 执行请求处理链
        chain.doFilter(request, response);

        long endTime = Instant.now()
                              .toEpochMilli();

        CustomUserDetails user;
        if (StrUtil.equals(httpRequest.getRequestURI(), "/login")) {
            user = null;
        } else {
            user = securityService.getCurrentUser();
        }

        systemLogRepository.save(SystemLog.getInstance(httpRequest, httpResponse, user, endTime - startTime));
        // 记录响应日志
        log.info("Response: {} {} - Status: {}", httpRequest.getMethod(), httpRequest.getRequestURI(),
                 httpResponse.getStatus());
    }
}
