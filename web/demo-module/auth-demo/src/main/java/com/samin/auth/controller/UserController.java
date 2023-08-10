package com.samin.auth.controller;

import com.samin.auth.service.UserService;
import com.samin.auth.vo.base.BaseResp;
import com.samin.auth.vo.req.PageReq;
import com.samin.auth.vo.req.UserSaveReq;
import com.samin.auth.vo.resp.PageResp;
import com.samin.auth.vo.resp.UserResp;
import com.samin.auth.vo.resp.UserSaveResp;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @PostMapping("/page")
    public PageResp<UserResp> page(@RequestBody PageReq req) {
        return userService.page(req);
    }

    @PostMapping("/save")
    public BaseResp<UserSaveResp> save(@RequestBody UserSaveReq userSaveReq) {
        return BaseResp.success(userService.saveUser(userSaveReq));
    }
}