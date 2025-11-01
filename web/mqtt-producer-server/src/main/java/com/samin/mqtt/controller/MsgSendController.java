package com.samin.mqtt.controller;

import com.samin.mqtt.service.MsgSendService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/msg")
@RequiredArgsConstructor
public class MsgSendController {

    private final MsgSendService msgSendService;
}
