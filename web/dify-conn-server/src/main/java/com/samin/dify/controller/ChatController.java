package com.samin.dify.controller;


import com.samin.dify.model.MsgReq;
import com.samin.dify.service.DifyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * AI 应用控制器
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

    /**
     * 聊天接口
     *
     * @param req
     * @return
     */
    @GetMapping("/chat")
    public String chat(@RequestBody MsgReq req) {
        return difyService.sendMessageToDify(req.getContent());
    }
}