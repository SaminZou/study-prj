package com.samin.auth.controller;

import com.samin.auth.service.LoginService;
import com.samin.auth.vo.BaseResp;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;

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
    public BaseResp<Void> login(@RequestBody HashMap<String, String> loginReq) {
        return loginService.login(loginReq);
    }
}