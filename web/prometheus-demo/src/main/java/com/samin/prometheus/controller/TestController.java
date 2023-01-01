package com.samin.prometheus.controller;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试控制类
 *
 * @author samin
 * @date 2022-12-07
 */
@Slf4j
@RestController
public class TestController {

    public static final Map<String, Object> map = new ConcurrentHashMap<>();

    @GetMapping("/timeOver3s")
    public ResponseEntity<String> timeOver3s() throws InterruptedException {
        Thread.sleep(3500);
        return ResponseEntity.ok().body("Ok");
    }

    @GetMapping("/something")
    public ResponseEntity<String> createLogs() {
        log.warn("Just checking");
        return ResponseEntity.ok().body("All Ok");
    }

    @GetMapping("/heap/test")
    public String testHeapUsed() {
        for (int i = 0; i < 10000000; i++) {
            map.put(i + "", new Object());
        }
        return "ok";
    }
}
