package com.samin.idempotent.controller;

import com.samin.idempotent.itf.RequestLock;
import com.samin.idempotent.vo.BaseResp;
import com.samin.idempotent.vo.BizAddReq;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class ApiIdempotentController {

    @RequestLock(prefix = "idtempotentTest", expire = 5000)
    @PostMapping("/api/idempotent")
    public BaseResp<Void> apiIdempotent(@RequestBody BizAddReq req) throws InterruptedException {
        log.info("api idempotent success");
        return BaseResp.success();
    }
}