package com.samin.demo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class CustomLoadConfirm {

    public CustomLoadConfirm() {
        log.info("主动加载测试确认...");
    }
}
