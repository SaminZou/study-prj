package com.samin.auth.config;

import com.samin.auth.repo.SystemLogRepository;
import com.samin.auth.service.SecurityService;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    private final SystemLogRepository systemLogRepository;
    private final SecurityService securityService;

    public WebConfig(SystemLogRepository systemLogRepository, SecurityService securityService) {
        this.systemLogRepository = systemLogRepository;
        this.securityService = securityService;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new SystemLogFilter(systemLogRepository, securityService));
    }
}
