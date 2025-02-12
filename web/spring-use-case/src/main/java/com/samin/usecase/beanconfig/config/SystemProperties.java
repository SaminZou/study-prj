package com.samin.usecase.beanconfig.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 需要 setter 和 getter
 *
 * @author samin
 * @date 2023-05-23
 */
@Setter
@Getter
@Component
@ConfigurationProperties(prefix = "custom")
public class SystemProperties {

    /**
     * 可以适配 pg_host / pg-host / pgHost
     */
    private String pgHost;

    private String pgPort;

    private String pgDatabase;

    private String pgUsername;

    private String pgPassword;

    private String pgSchema;

    private String redisHost;

    private String redisPort;

    private String redisPassword;

    private String redisDatabase;
}
