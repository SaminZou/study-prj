package com.samin.mqtt.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.mqtt.outbound.MqttPahoMessageHandler;
import org.springframework.integration.mqtt.support.MqttHeaders;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.handler.annotation.Header;

@Slf4j
@Configuration
public class MqttOutboundConfig {

    @Autowired
    private MqttOutboundPoolConfig mqttOutboundPoolConfig;

    @Bean
    public MessageChannel mqttOutboundChannel() {
        return new DirectChannel();
    }

    @Bean
    @ServiceActivator(inputChannel = "mqttOutboundChannel")
    public MessageHandler mqttOutboundMessageHandler() {
        return message -> {
            String topic = (String) message.getHeaders()
                                           .get(MqttHeaders.TOPIC);
            Object payload = message.getPayload();

            MqttPahoMessageHandler handler = mqttOutboundPoolConfig.getHandler();

            try {
                handler.handleMessage(MessageBuilder.withPayload(payload)
                                                    .setHeader(MqttHeaders.TOPIC, topic)
                                                    .build());
            } catch (Exception e) {
                log.error("MQTT 发布失败，尝试重连: {}", e.getMessage());
                // 尝试重启 handler
                try {
                    handler.stop();
                    handler.start();
                    handler.handleMessage(MessageBuilder.withPayload(payload)
                                                        .setHeader(MqttHeaders.TOPIC, topic)
                                                        .build());
                } catch (Exception retryEx) {
                    log.error("MQTT 重新发布仍失败: {}", retryEx.getMessage(), retryEx);
                }
            }
        };
    }

    @MessagingGateway(defaultRequestChannel = "mqttOutboundChannel")
    public interface MqttGateway {

        void send(@Header(MqttHeaders.TOPIC) String topic, String payload);
    }
}
