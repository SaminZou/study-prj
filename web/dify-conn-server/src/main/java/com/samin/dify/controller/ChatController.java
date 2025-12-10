package com.samin.dify.controller;

import com.samin.dify.model.MsgReq;
import com.samin.dify.model.ResponseResult;
import com.samin.dify.service.DifyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

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
@Validated
public class ChatController {

    private final DifyService difyService;

    /**
     * 聊天接口
     *
     * @param req 消息请求
     * @return 统一响应结果
     */
    @PostMapping("/chat")
    public ResponseResult<String> chat(@Valid @RequestBody MsgReq req) {
        log.info("收到聊天请求，内容: {}", req.getContent());
        String answer = difyService.sendMessageToDify(req.getContent());
        return ResponseResult.success(answer);
    }
}