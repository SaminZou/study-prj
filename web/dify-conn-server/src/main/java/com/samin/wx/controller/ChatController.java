package com.samin.wx.controller;


import com.samin.wx.model.MsgReq;
import com.samin.wx.service.DifyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * ai 应用
 * <p>
 * Created By: Samin
 * <p>
 * Created Date: 2025-05-23
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/dify")
public class ChatController {

    private final DifyService difyService;

    @GetMapping("/chat")
    public String login(@RequestBody MsgReq req) {
        return difyService.sendMessageToDify(req.getContent());
    }
}