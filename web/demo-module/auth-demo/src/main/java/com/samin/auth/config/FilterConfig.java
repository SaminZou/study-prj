package com.samin.auth.config;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class FilterConfig {

    private final SystemLogFilter systemLogFilter;

    @Bean
    public FilterRegistrationBean<SystemLogFilter> loggingFilterRegistration() {
        FilterRegistrationBean<SystemLogFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(systemLogFilter);
        // 设置过滤器拦截的 URL 路径
        registrationBean.addUrlPatterns("/*");
        // 设置过滤器执行顺序
        registrationBean.setOrder(1);
        return registrationBean;
    }
}
