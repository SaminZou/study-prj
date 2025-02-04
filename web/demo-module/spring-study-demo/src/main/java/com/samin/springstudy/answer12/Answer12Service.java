package com.samin.springstudy.answer12;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class Answer12Service implements CommandLineRunner {

    @Value("${test-boolean}")
    private boolean testBoolean;

    public Answer12Service() {
        log.info("test boolean in Constructor: {}", testBoolean);
    }

    @Override
    public void run(String... args) throws Exception {
        log.info("test boolean after Constructor: {}", testBoolean);
    }
}
