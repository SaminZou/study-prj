package com.samin.mqtt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class MqttConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(MqttConsumerApplication.class, args);
    }
}
