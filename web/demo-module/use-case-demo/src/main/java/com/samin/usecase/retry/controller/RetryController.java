package com.samin.usecase.retry.controller;

import com.samin.usecase.retry.service.RetryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RetryController {

    @Autowired
    private RetryService retryService;

    @GetMapping("/retry")
    public String retry() throws Exception {
        int test = retryService.test(0);
        System.out.println("返回结果：" + test);
        return "success";
    }
}
