package com.samin.worker.controller;

import com.samin.provider.CustomClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
public class TestController {

    private final CustomClient customClient;

    @GetMapping("/test")
    public String test() {
        return customClient.showKey();
    }
}
