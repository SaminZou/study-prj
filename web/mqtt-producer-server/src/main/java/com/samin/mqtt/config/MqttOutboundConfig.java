package com.samin.mqtt.config;

import java.time.Instant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.mqtt.core.MqttPahoClientFactory;
import org.springframework.integration.mqtt.outbound.MqttPahoMessageHandler;
import org.springframework.integration.mqtt.support.DefaultPahoMessageConverter;
import org.springframework.integration.mqtt.support.MqttHeaders;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.handler.annotation.Header;

@Configuration
public class MqttOutboundConfig {

    @Value("${mqtt.client-id-prefix}")
    private String clientId;
    @Autowired
    private MqttPahoClientFactory mqttClientFactory;

    @Bean
    public MessageChannel mqttOutboundChannel() {
        DirectChannel directChannel = new DirectChannel();
        directChannel.subscribe(mqttOutboundMessageHandler());
        return directChannel;
    }

    @Bean
    @ServiceActivator(inputChannel = "mqttOutboundChannel")
    public MessageHandler mqttOutboundMessageHandler() {
        return message -> {
            // MqttPahoMessageHandler 用于发布
            MqttPahoMessageHandler handler = new MqttPahoMessageHandler(clientId + Instant.now()
                                                                                          .toEpochMilli(),
                                                                        mqttClientFactory);

            handler.setAsync(true);
            handler.setDefaultQos(0);
            handler.setConverter(new DefaultPahoMessageConverter());
            handler.setDefaultRetained(false);

            String topic = (String) message.getHeaders()
                                           .get(MqttHeaders.TOPIC);
            String payload = (String) message.getPayload();
            handler.handleMessage(MessageBuilder.withPayload(payload)
                                                .setHeader(MqttHeaders.TOPIC, topic)
                                                .build());
        };
    }

    @MessagingGateway(defaultRequestChannel = "mqttOutboundChannel")
    public interface MqttGateway {

        void send(@Header(MqttHeaders.TOPIC) String topic, String payload);
    }
}
