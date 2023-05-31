package com.samin.auth.controller;

import com.samin.auth.service.LoginService;

import java.util.Base64;
import java.util.HashMap;
import javax.annotation.Resource;

import com.samin.auth.vo.BaseResp;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 登录控制器
 *
 * @author samin
 * @date 2022-08-08
 */
@RestController
public class LoginController {

    @Resource
    private LoginService loginService;

    @PostMapping(value = "/login")
    public BaseResp login(@RequestBody HashMap<String, String> loginReq) {
        return loginService.login(loginReq);
    }
}