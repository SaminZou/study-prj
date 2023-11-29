package com.samin.dingtalk.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.samin.dingtalk.pojo.req.AlertReq;
import com.samin.dingtalk.service.DingtalkService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@Api(tags = "钉钉通知控制类")
public class DingtalkController {

    private final ObjectMapper objectMapper;
    private final DingtalkService dingtalkService;

    @ApiOperation("钉钉通知接口")
    @PostMapping("/dingtalk")
    public String dingtalk(@RequestBody AlertReq req) throws JsonProcessingException {
        log.info("请求内容为：{}", objectMapper.writeValueAsString(req));

        dingtalkService.dingtalk(req);

        return "success";
    }
}