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
        try {
            String topic = (String) message.getHeaders().get(MqttHeaders.RECEIVED_TOPIC);
            if (topic == null) {
                log.warn("收到 MQTT 消息但缺少 topic，header: {}", message.getHeaders());
                return;
            }
            byte[] payloadBytes = (byte[]) message.getPayload();
            String payload = new String(payloadBytes, StandardCharsets.UTF_8);
            log.info("topic: {}, payload: {}", topic, payload);
        } catch (Exception e) {
            log.error("处理 MQTT 消息异常，消息内容: {}", message, e);
        }
    }
}