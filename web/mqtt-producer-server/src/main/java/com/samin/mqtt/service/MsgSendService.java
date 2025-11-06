package com.samin.mqtt.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.samin.mqtt.config.MqttOutboundConfig;
import com.samin.mqtt.model.IoTDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MsgSendService {

    @Autowired
    private MqttOutboundConfig.MqttGateway mqttGateway;
    @Autowired
    private ObjectMapper objectMapper;

    public void send() throws JsonProcessingException {
        IoTDTO dto = new IoTDTO();
        dto.setCode("0");
        dto.setUuid("DEVICE001");
        dto.setParams("");
        String topic = "/sys/type001/device001/thing/event/beacon/post";
        mqttGateway.send(topic, objectMapper.writeValueAsString(dto));
    }
}
