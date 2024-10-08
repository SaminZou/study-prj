package com.samin.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class SpringCloudAlibabaApp2 {

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudAlibabaApp2.class, args);
    }
}
