package com.samin.sync.config;

import java.util.Arrays;
import java.util.Collections;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class CorsConfig {

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();

        // 设置允许的跨域请求来源；允许所有域名
        config.setAllowedOriginPatterns(Collections.singletonList("*"));

        // 设置允许的 HTTP 方法
        config.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));

        // 设置允许的请求头
        config.setAllowedHeaders(Collections.singletonList("*"));

        // 设置是否允许发送 Cookie
        config.setAllowCredentials(true);

        source.registerCorsConfiguration("/**", config); // 对所有 URL 生效
        return new CorsFilter(source);
    }
}