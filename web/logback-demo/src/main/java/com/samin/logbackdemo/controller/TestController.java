package com.samin.logbackdemo.controller;

import com.samin.logbackdemo.service.TestService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
public class TestController {

    private final TestService testService;

    @GetMapping("/test/log_level")
    public void testLogLevel() {
        log.trace("trace...");
        log.debug("debug...");
        log.info("info...");
        log.warn("warn...");
        log.error("error...");

        log.error("error: {}", "something wrong");
        log.error("error: {}", "something wrong", new NullPointerException());
    }

    @GetMapping("/test/times")
    public void times() {
        testService.setTimes();
    }
}
