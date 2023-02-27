package com.samin.provider.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class CustomLoadCofirm {

    public CustomLoadCofirm() {
        log.info("主动加载测试确认...");
    }
}
