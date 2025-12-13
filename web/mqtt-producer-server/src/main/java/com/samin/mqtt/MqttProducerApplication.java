package com.samin.mqtt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class MqttProducerApplication {

    public static void main(String[] args) {
        SpringApplication.run(MqttProducerApplication.class, args);
    }
}
