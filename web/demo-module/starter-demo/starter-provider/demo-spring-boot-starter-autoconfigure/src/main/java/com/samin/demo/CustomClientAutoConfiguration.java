package com.samin.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnProperty(value = "custom.part1.custom-key")
@EnableConfigurationProperties(CustomProperties.class)
public class CustomClientAutoConfiguration {

    @Autowired
    private CustomProperties customProperties;

    @Bean
    public CustomClient customClient() {
        return new CustomClient(customProperties);
    }
}
