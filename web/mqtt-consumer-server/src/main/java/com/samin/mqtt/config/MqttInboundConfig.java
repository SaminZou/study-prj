package com.samin.mqtt.config;

import com.samin.mqtt.service.MqttInboundMessageHandler;
import java.time.Instant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

@Configuration
public class MqttInboundConfig {

    @Value("${mqtt.client-id-prefix}")
    private String clientId;
    @Value("${biz.consumer-topic}")
    private String consumerTopic;
    @Autowired
    private MqttInboundMessageHandler mqttInboundMessageHandler;

    @Bean
    public MessageChannel mqttInboundChannel() {
        return new DirectChannel();
    }

    @Bean
    public MessageProducerSupport mqttInbound(MqttPahoClientFactory mqttClientFactory) {
        final DefaultPahoMessageConverter converter = new DefaultPahoMessageConverter();
        converter.setPayloadAsBytes(true);

        // MqttPahoMessageDrivenChannelAdapter 用于订阅
        MqttPahoMessageDrivenChannelAdapter adapter = new MqttPahoMessageDrivenChannelAdapter(clientId + Instant.now()
                                                                                                                .toEpochMilli(),
                                                                                              mqttClientFactory,
                                                                                              consumerTopic);
        adapter.setConverter(converter);
        adapter.setQos(0);
        adapter.setOutputChannel(mqttInboundChannel());
        return adapter;
    }

    @Bean
    @ServiceActivator(inputChannel = "mqttInboundChannel")
    public MessageHandler inboundMessageHandler() {
        return mqttInboundMessageHandler;
    }
}