package com.samin.adminclient.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试控制类
 */
@RestController
public class TestController {

    @GetMapping(value = "/test")
    public String test() {
        return "success";
    }
}