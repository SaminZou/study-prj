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

    /**
     * 服务器地址，示例：tcp://127.0.0.1:1883
     */
    @NotBlank(message = "mqtt.url 不能为空")
    private String url;

    /**
     * 账号（可选）
     */
    private String username;

    /**
     * 密码（可选）
     */
    private String password;

    /**
     * 客户端 ID 前缀
     */
    @NotBlank(message = "mqtt.client-id-prefix 不能为空")
    private String clientIdPrefix = "consumer-";

    /**
     * Qos 默认级别
     */
    private int qos = 0;

    /**
     * 心跳秒数
     */
    @Positive
    private int keepAlive = 20;

    /**
     * 连接超时秒数
     */
    @Positive
    private int connectionTimeout = 10;

    /**
     * 是否清理会话
     */
    private boolean cleanSession = true;
}


