package com.samin.mqtt.service;

import java.nio.charset.StandardCharsets;
import lombok.extern.slf4j.Slf4j;
import org.springframework.integration.mqtt.support.MqttHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHandler;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class MqttInboundMessageHandler implements MessageHandler {

    @Override
    public void handleMessage(Message<?> message) {
        String topic = (String) message.getHeaders()
                                       .get(MqttHeaders.RECEIVED_TOPIC);
        String[] topics = topic.split("/");
        String type = topics[6];
        String payload = new String((byte[]) message.getPayload(), StandardCharsets.UTF_8);

        log.info("topic:{}, payload:{}", topic, payload);
    }
}