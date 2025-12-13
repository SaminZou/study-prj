package com.samin.mqtt.config;

import com.samin.mqtt.service.MqttInboundMessageHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.endpoint.MessageProducerSupport;
import org.springframework.integration.mqtt.core.MqttPahoClientFactory;
import org.springframework.integration.mqtt.inbound.MqttPahoMessageDrivenChannelAdapter;
import org.springframework.integration.mqtt.support.DefaultPahoMessageConverter;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;

import java.util.Arrays;
import java.util.UUID;

@Configuration
@RequiredArgsConstructor
public class MqttInboundConfig {

    private final MqttProperties mqttProperties;
    private final BizProperties bizProperties;
    private final MqttInboundMessageHandler mqttInboundMessageHandler;

    @Bean
    public MessageChannel mqttInboundChannel() {
        return new DirectChannel();
    }

    @Bean
    public MessageProducerSupport mqttInbound(MqttPahoClientFactory mqttClientFactory) {
        final DefaultPahoMessageConverter converter = new DefaultPahoMessageConverter();
        converter.setPayloadAsBytes(true);

        // MqttPahoMessageDrivenChannelAdapter 用于订阅
        String clientId = mqttProperties.getClientIdPrefix() + "sub-" + UUID.randomUUID();
        String[] topics = Arrays.stream(bizProperties.getConsumerTopic().split(","))
                                .map(String::trim)
                                .filter(s -> !s.isEmpty())
                                .toArray(String[]::new);

        MqttPahoMessageDrivenChannelAdapter adapter = new MqttPahoMessageDrivenChannelAdapter(
                clientId,
                mqttClientFactory,
                topics
        );
        adapter.setConverter(converter);
        adapter.setQos(mqttProperties.getQos());
        adapter.setOutputChannel(mqttInboundChannel());
        return adapter;
    }

    @Bean
    @ServiceActivator(inputChannel = "mqttInboundChannel")
    public MessageHandler inboundMessageHandler() {
        return mqttInboundMessageHandler;
    }
}