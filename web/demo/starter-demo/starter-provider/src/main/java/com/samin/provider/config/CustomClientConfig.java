package com.samin.provider.config;

import com.samin.provider.CustomClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CustomClientConfig {

    @Bean
    public CustomClient customClient() {
        return new CustomClient();
    }
}
