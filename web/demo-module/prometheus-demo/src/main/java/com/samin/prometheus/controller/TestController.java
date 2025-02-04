package com.samin.prometheus.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 测试控制类
 *
 * @author samin
 * @date 2022-12-07
 */
@Slf4j
@RestController
public class TestController {

    public static final Map<String, Object> MAP = new ConcurrentHashMap<>();

    @GetMapping("/timeOver3s")
    public ResponseEntity<String> timeOver3s() throws InterruptedException {
        Thread.sleep(3500);
        return ResponseEntity.ok()
                             .body("Ok");
    }

    @GetMapping("/timeOver5s")
    public ResponseEntity<String> timeOver5s() throws InterruptedException {
        Thread.sleep(5500);
        return ResponseEntity.ok()
                             .body("Ok");
    }

    @GetMapping("/something")
    public ResponseEntity<String> createLogs() {
        log.warn("Just checking");
        return ResponseEntity.ok()
                             .body("All Ok");
    }

    @GetMapping("/heap/test")
    public String testHeapUsed() {
        for (int i = 0; i < 10000000; i++) {
            MAP.put(String.valueOf(i), new Object());
        }
        return "ok";
    }
}
