package com.samin.demo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CustomClient {

    private CustomProperties customProperties;

    public CustomClient(CustomProperties customProperties) {
        this.customProperties = customProperties;
    }

    public String showKey() {
        log.info("custom.part1.custom-key: {}", customProperties.getPart1()
                                                                .getCustomKey());
        log.info("custom.part2.key: {}", customProperties.getPart2()
                                                         .getKey());
        return customProperties.getPart1()
                               .getCustomKey();
    }
}
