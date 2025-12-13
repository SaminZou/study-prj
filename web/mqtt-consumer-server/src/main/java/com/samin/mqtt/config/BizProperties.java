package com.samin.mqtt.config;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

/**
 * 业务相关配置
 */
@Data
@Validated
@ConfigurationProperties(prefix = "biz")
public class BizProperties {

    /**
     * 订阅主题，可逗号分隔多个
     */
    @NotBlank(message = "biz.consumer-topic 不能为空")
    private String consumerTopic;
}


