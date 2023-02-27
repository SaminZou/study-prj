package com.samin.provider;

import com.samin.provider.properties.Custom;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CustomClient {

    @Autowired
    private Custom custom;

    public String showKey() {
        log.info("custom.part1.custom-key: {}", custom.getPart1().getCustomKey());
        log.info("custom.part2.key: {}", custom.getPart2().getKey());
        return custom.getPart1().getCustomKey();
    }
}
