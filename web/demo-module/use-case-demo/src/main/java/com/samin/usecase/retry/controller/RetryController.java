package com.samin.usecase.retry.controller;

import com.samin.usecase.retry.service.RetryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class RetryController {

    private final RetryService retryService;

    @GetMapping("/retry/{code}")
    public String retry(@PathVariable(value = "code") int code) throws Exception {
        int test = retryService.retry(code);
        System.out.println("返回结果：" + test);
        return "success";
    }
}
