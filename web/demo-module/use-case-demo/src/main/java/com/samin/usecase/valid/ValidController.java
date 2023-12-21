package com.samin.usecase.valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class ValidController {

    /**
     * Get 请求方式的对象入参，需要添加注解
     *
     * @param req
     * @return
     */
    @GetMapping("/valid")
    public String valid(@Valid ValidReq req) {
        return "success: " + req.getCol();
    }

    @PostMapping("/valid/test")
    public void validTest(@RequestBody @Valid Req req) {
    }
}
