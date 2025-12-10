package com.samin.dify;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;

/**
 * Dify 连接服务启动类
 */
@SpringBootApplication
public class DifyConnApplication {

    public static void main(String[] args) {
        SpringApplication.run(DifyConnApplication.class, args);
    }

    /**
     * 配置 RestTemplate Bean
     * 设置连接超时、读取超时等参数
     */
    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
        // 连接超时：5秒
        factory.setConnectTimeout(5000);
        // 读取超时：30秒（AI 响应可能需要较长时间）
        factory.setReadTimeout(30000);

        return builder
                .requestFactory(() -> factory)
                .setConnectTimeout(Duration.ofSeconds(5))
                .setReadTimeout(Duration.ofSeconds(30))
                .build();
    }
}
