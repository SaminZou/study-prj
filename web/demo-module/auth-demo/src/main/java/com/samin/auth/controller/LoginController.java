package com.samin.auth.controller;

import com.samin.auth.service.LoginService;
import com.samin.auth.vo.base.BaseResp;
import java.util.HashMap;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 登录控制器
 * <p>
 * Description: 登录控制器
 * <p>
 * Created By: Samin
 * <p>
 * Created Date: 2023-08-25
 */
//@Api("登录控制器")
@RestController
@RequiredArgsConstructor
public class LoginController {

    private final LoginService loginService;

    @PostMapping(value = "/login")
    public BaseResp<String> login(@RequestBody HashMap<String, String> loginReq) {
        return loginService.login(loginReq);
    }
}