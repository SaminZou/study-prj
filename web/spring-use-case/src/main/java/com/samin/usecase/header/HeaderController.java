package com.samin.usecase.header;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class HeaderController {

    @PostMapping("/header/test")
    public void test(@RequestHeader("X-test") String test) {
        log.info("header: {}", test);
    }
}
