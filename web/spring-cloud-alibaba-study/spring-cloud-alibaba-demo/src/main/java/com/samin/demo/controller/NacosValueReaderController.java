package com.samin.demo.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RefreshScope
@RestController
public class NacosValueReaderController {

    @Value("${blogTitle:default}")
    private String blogTitle;

    @GetMapping("/blogTitle")
    public String blogTitle() {
        return blogTitle;
    }
}