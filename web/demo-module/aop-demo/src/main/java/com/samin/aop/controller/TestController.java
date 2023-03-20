package com.samin.aop.controller;

import com.samin.aop.annotation.TimeCount;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试控制类
 *
 * @author samin
 * @date 2022-08-11
 */
@RestController
public class TestController {

    @GetMapping(value = "/test")
    public String test(@RequestParam(value = "param") String param) {
        return "success";
    }

    @TimeCount
    @GetMapping(value = "/timeCount")
    public String timeCount() throws InterruptedException {
        Thread.sleep((long) (Math.random() * 4 + 1) * 1000L);
        return "success";
    }
}