package com.samin.mqtt.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.samin.mqtt.model.IoTDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Slf4j
@EnableScheduling
@Configuration
public class TaskConfig {

    @Autowired
    private MqttOutboundConfig.MqttGateway mqttGateway;
    @Autowired
    private ObjectMapper objectMapper;

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
    }
}
