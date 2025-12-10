package com.samin.dify.config;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;

/**
 * Dify API 配置
 */
@Slf4j
@Data
@Configuration
@Validated
@ConfigurationProperties(prefix = "dify")
public class DifyApiConfig {

    /**
     * API Key
     */
    @NotBlank(message = "Dify API Key 不能为空")
    private String apiKey;

    /**
     * API URL
     */
    @NotBlank(message = "Dify API URL 不能为空")
    private String apiUrl;

    /**
     * 用户标识，默认为 server-001
     */
    private String userId = "server-001";
}