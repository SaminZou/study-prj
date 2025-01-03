package com.samin.minio.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 全局跨域处理
 * <p>
 * Description: 全局跨域处理
 * <p>
 * Created By: Samin
 * <p>
 * Created Date: 2024-06-26
 */
@Configuration
public class CorsConfig implements WebMvcConfigurer {

    private CorsConfiguration buildConfig() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowCredentials(true); // 允许cookies跨域
        corsConfiguration.addAllowedOriginPattern("*");
        // #允许向该服务器提交请求的URI，*表示全部允许，在 SpringMVC 中，如果设成*，会自动转成当前请求头中的Origin
        // config.addAllowedOrigin("*");
        corsConfiguration.addAllowedHeader("*");
        // #允许访问的头信息,*表示全部
        corsConfiguration.setMaxAge(18000L);
        // 预检请求的缓存时间（秒），即在这个时间段里，对于相同的跨域请求不会再预检了
        corsConfiguration.addAllowedMethod("OPTIONS");
        // 允许提交请求的方法类型，*表示全部允许
        corsConfiguration.addAllowedMethod("HEAD");
        corsConfiguration.addAllowedMethod("GET");
        corsConfiguration.addAllowedMethod("PUT");
        corsConfiguration.addAllowedMethod("POST");
        corsConfiguration.addAllowedMethod("DELETE");
        corsConfiguration.addAllowedMethod("PATCH");
        return corsConfiguration;
    }

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", buildConfig());
        return new CorsFilter(source);
    }

}
