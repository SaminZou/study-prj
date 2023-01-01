package com.samin.dingtalk.controller;

import com.samin.dingtalk.pojo.req.AlertReq;
import com.samin.dingtalk.service.DingtalkService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class DingtalkController {

    private final DingtalkService dingtalkService;

    @PostMapping("/dingtalk")
    public String dingtalk(@RequestBody AlertReq req) {
        dingtalkService.dingtalk(req);

        return "success";
    }
}