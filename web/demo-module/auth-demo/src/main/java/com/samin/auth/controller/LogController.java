package com.samin.auth.controller;

import com.samin.auth.service.LogService;
import com.samin.auth.vo.req.PageReq;
import com.samin.auth.vo.resp.LogResp;
import com.samin.auth.vo.resp.PageResp;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 日志控制类
 * <p>
 * Description: 日志控制类
 * <p>
 * Created By: Samin
 * <p>
 * Created Date: 2023-09-17
 */
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/log")
public class LogController {

    private final LogService logService;

    @PostMapping("/page")
    public PageResp<LogResp> page(@RequestBody PageReq req) {
        return logService.page(req);
    }
}