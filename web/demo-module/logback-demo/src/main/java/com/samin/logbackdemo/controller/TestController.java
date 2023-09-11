package com.samin.logbackdemo.controller;

import com.samin.logbackdemo.pojo.BizLogBO;
import com.samin.logbackdemo.service.TestService;
import java.time.Instant;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
public class TestController {

    private final TestService testService;

    @GetMapping("/test/log_level")
    public void testLogLevel() {
        long startTime = Instant.now()
                                .toEpochMilli();
        log.trace("trace...");
        log.debug("debug...");
        log.info("info...");
        log.warn("warn...");

        BizLogBO bizLog = BizLogBO.builder()
                                  .id(1L)
                                  .eventName("testLogLevel")
                                  .userId("samin")
                                  .resultMsg("")
                                  .costTime(Instant.now()
                                                   .toEpochMilli() - startTime)
                                  .request("")
                                  .response("")
                                  .others("")
                                  .build();

        log.error("info: [{}]", bizLog);

        log.error("error: [{}]", "something wrong");
        log.error("error: [{}]", "something wrong", new NullPointerException());
    }

    @GetMapping("/test/times")
    public void times() {
        testService.setTimes();
    }
}
