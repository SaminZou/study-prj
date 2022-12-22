package com.samin.logbackdemo.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class TestService {

    private int times = 0;

    public void setTimes() {
        times += 1;
        log.info("times count 1 ... now is: {}", times);
    }
}
