package com.samin.redis.controller;

import com.samin.redis.service.LockTestService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class LockTestController {

    private final LockTestService lockTestService;

    /**
     * 5s 内两个请求同时过来，只有一个能拿到锁
     *
     * @throws InterruptedException
     */
    @PostMapping("/lock/test")
    public void stats() throws InterruptedException {
        lockTestService.testRedissonLock();
    }
}