package com.samin.usecase.beanconfig.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 根据配置来决定是否生效
 *
 * @author samin
 * @date 2022-12-29
 */
@Slf4j
@Configuration
@ConditionalOnProperty(value = {"custom.config.enabled"}, havingValue = "true", matchIfMissing = true)
public class ConditionalConfig {

    @Bean
    public String test() {
        log.info("[启动时加载] test String bean inject...");
        return "test";
    }
}
