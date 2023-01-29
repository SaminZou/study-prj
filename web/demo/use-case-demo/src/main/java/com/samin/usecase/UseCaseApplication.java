package com.samin.usecase;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class UseCaseApplication {

    public static void main(String[] args) {
        SpringApplication.run(UseCaseApplication.class, args);
    }
}