package com.samin.usecase.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 根据配置来决定是否生效
 *
 * @author samin
 * @date 2022-12-29
 */
@Configuration
@ConditionalOnProperty(value = {"custom.config.enabled"}, havingValue = "true", matchIfMissing = true)
public class ConditionalConfig {

    @Bean
    public String test(){
        System.out.println("test String bean inject...");
        return "test";
    }
}