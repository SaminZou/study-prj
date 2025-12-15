package com.samin.mqtt.config;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

/**
 * MQTT 基础配置
 */
@Data
@Validated
@ConfigurationProperties(prefix = "mqtt")
public class MqttProperties {

    @NotBlank(message = "mqtt.url 不能为空")
    private String url;

    private String username;

    private String password;

    @NotBlank(message = "mqtt.client-id-prefix 不能为空")
    private String clientIdPrefix = "producer-";

    private int qos = 0;

    @Positive
    private int keepAlive = 20;

    @Positive
    private int connectionTimeout = 10;

    private boolean cleanSession = true;

    /**
     * 生产端客户端池大小
     */
    @Positive
    private int poolSize = 4;
}