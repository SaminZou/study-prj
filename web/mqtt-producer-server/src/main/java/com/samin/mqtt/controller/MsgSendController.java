package com.samin.mqtt.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.samin.mqtt.service.MsgSendService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/msg")
@RequiredArgsConstructor
public class MsgSendController {

    private final MsgSendService msgSendService;

    @PostMapping("/send")
    public String sendMsg() throws JsonProcessingException {
        msgSendService.send();
        return "success";
    }
}
