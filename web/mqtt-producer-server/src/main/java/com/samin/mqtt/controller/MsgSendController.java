package com.samin.mqtt.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.samin.mqtt.model.IoTDTO;
import com.samin.mqtt.service.MsgSendService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/msg")
@RequiredArgsConstructor
public class MsgSendController {

    private final MsgSendService msgSendService;

    /**
     * 发送 MQTT 消息
     *
     * @param dto   消息内容
     * @param topic 可选 topic，未填使用默认
     */
    @PostMapping("/send")
    public String sendMsg(@Valid @RequestBody IoTDTO dto,
                          @RequestParam(value = "topic", required = false) String topic) throws JsonProcessingException {
        msgSendService.send(dto, topic);
        return "success";
    }
}
