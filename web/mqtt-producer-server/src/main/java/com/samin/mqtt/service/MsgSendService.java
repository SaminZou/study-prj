package com.samin.mqtt.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.samin.mqtt.config.MqttOutboundConfig;
import com.samin.mqtt.model.IoTDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Slf4j
@Service
@RequiredArgsConstructor
public class MsgSendService {

    private static final String DEFAULT_TOPIC = "/sys/type001/device001/thing/event/beacon/post";

    private final MqttOutboundConfig.MqttGateway mqttGateway;
    private final ObjectMapper objectMapper;

    public void send(IoTDTO dto, String topic) throws JsonProcessingException {
        String targetTopic = StringUtils.hasText(topic) ? topic : DEFAULT_TOPIC;
        mqttGateway.send(targetTopic, objectMapper.writeValueAsString(dto));
        log.info("发送 MQTT 消息，topic={}, dto={}", targetTopic, dto);
    }
}
