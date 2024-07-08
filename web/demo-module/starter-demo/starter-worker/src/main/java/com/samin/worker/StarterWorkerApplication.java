package com.samin.worker;

import com.samin.demo.EnableCustomLoadConfirm;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableCustomLoadConfirm
@SpringBootApplication
public class StarterWorkerApplication {

    public static void main(String[] args) {
        SpringApplication.run(StarterWorkerApplication.class, args);
    }
}
