package com.samin.auth.controller;

import com.samin.auth.service.LoginService;
import com.samin.auth.vo.base.BaseResp;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

/**
 * 登录控制器
 *
 * @author samin
 * @date 2022-08-08
 */
@RestController
@RequiredArgsConstructor
public class LoginController {

    private final LoginService loginService;

    @PostMapping(value = "/login")
    public BaseResp<String> login(@RequestBody HashMap<String, String> loginReq) {
        return loginService.login(loginReq);
    }
}