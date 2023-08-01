package com.samin.auth.controller;

import com.samin.auth.service.UserService;
import com.samin.auth.vo.BaseResp;
import com.samin.auth.vo.UserSaveResp;
import com.samin.auth.vo.UserVo;
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

    @PostMapping("/save")
    public BaseResp<UserSaveResp> save(@RequestBody UserVo userVo) {
        return BaseResp.success(userService.saveUser(userVo));
    }
}
