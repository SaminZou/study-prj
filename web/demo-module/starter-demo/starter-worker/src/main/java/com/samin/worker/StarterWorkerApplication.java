package com.samin.worker;

import com.samin.demo.EnabelCustomLoadConfirm;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnabelCustomLoadConfirm
@SpringBootApplication
public class StarterWorkerApplication {

    public static void main(String[] args) {
        SpringApplication.run(StarterWorkerApplication.class, args);
    }
}
