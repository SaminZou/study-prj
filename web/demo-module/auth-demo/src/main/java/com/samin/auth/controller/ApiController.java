package com.samin.auth.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 提供第三方的 API
 * <p>
 * Description: HttpSecurityConfig 配置绕过认证供第三方使用
 * <p>
 * Created By: Samin
 * <p>
 * Created Date: 2024-03-18
 */
@RestController
@RequestMapping("/api")
public class ApiController {

    @GetMapping("/getUserList")
    public String getUserList() {
        return "success";
    }
}
