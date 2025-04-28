package com.samin.idempotence.controller;

import com.samin.idempotence.itf.RequestLock;
import com.samin.idempotence.vo.BaseResp;
import com.samin.idempotence.vo.BizAddReq;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class ApiIdempotenceController {

    @RequestLock(prefix = "idtempotenceTest", expire = 5000)
    @PostMapping("/api/idempotence")
    public BaseResp<Void> apiIdempotence(@RequestBody BizAddReq req) throws InterruptedException {
        log.info("api idempotence success");
        return BaseResp.success();
    }
}