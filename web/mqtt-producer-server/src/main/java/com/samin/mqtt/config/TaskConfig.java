package com.samin.mqtt.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.samin.mqtt.model.IoTDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Slf4j
@EnableScheduling
@Configuration
@RequiredArgsConstructor
@ConditionalOnProperty(prefix = "mqtt.task", name = "enabled", havingValue = "true", matchIfMissing = true)
public class TaskConfig {

    private final MqttOutboundConfig.MqttGateway mqttGateway;
    private final ObjectMapper objectMapper;

    @Scheduled(fixedRate = 5000)
    public void task() throws JsonProcessingException {
        IoTDTO dto = new IoTDTO();
        dto.setCode("0");
        dto.setUuid("DEVICE001");
        dto.setParams("");

        send(dto);
    }

    private void send(IoTDTO dto) throws JsonProcessingException {
        String topic = "/sys/type001/device001/thing/event/beacon/post";
        mqttGateway.send(topic, objectMapper.writeValueAsString(dto));
        log.debug("定时发送 MQTT 消息，topic={}, payload={}", topic, dto);
    }
}
