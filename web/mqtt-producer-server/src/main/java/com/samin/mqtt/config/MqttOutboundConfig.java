package com.samin.mqtt.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.mqtt.support.MqttHeaders;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.handler.annotation.Header;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class MqttOutboundConfig {

    private final MqttOutboundPoolConfig mqttOutboundPoolConfig;

    @Bean
    public MessageChannel mqttOutboundChannel() {
        return new DirectChannel();
    }

    @Bean
    @ServiceActivator(inputChannel = "mqttOutboundChannel")
    public MessageHandler mqttOutboundMessageHandler() {
        return message -> {
            String topic = (String) message.getHeaders().get(MqttHeaders.TOPIC);
            Object payload = message.getPayload();

            try {
                mqttOutboundPoolConfig.getHandler().handleMessage(
                        MessageBuilder.withPayload(payload)
                                      .setHeader(MqttHeaders.TOPIC, topic)
                                      .build()
                );
            } catch (Exception e) {
                log.error("MQTT 发布失败，topic={}, 原因={}", topic, e.getMessage(), e);
            }
        };
    }

    @MessagingGateway(defaultRequestChannel = "mqttOutboundChannel")
    public interface MqttGateway {

        void send(@Header(MqttHeaders.TOPIC) String topic, String payload);
    }
}
