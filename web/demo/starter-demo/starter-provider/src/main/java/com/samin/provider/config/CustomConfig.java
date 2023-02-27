package com.samin.provider.config;

import com.samin.provider.properties.Custom;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class CustomConfig {

    public CustomConfig() {
        log.info("已载入 CustomConfig");
    }

    @Bean
    public Custom custom() {
        return new Custom();
    }
}
