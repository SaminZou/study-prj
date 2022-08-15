package com.samin.logbackdemo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class TestController {

    @GetMapping("/test/log_level")
    public void testLogLevel() {
        log.trace("trace...");
        log.debug("debug...");
        log.info("info...");
        log.warn("warn...");
        log.error("error...");
    }
}
