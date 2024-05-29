package com.samin.jobadmin.controller;

import com.samin.jobadmin.bean.JobVo;
import com.samin.jobadmin.bean.UserVo;
import com.samin.jobadmin.service.UserService;
import com.samin.jobsdk.bean.BaseResp;
import com.samin.jobsdk.bean.PageReq;
import com.samin.jobsdk.bean.PageResp;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/page")
    public BaseResp<PageResp<UserVo>> page(PageReq<Void> req) {
        return BaseResp.success(userService.page(req));
    }

    @PostMapping("/disable/{id}")
    public BaseResp<Void> disable(@PathVariable Integer id) {
        userService.disable(id);
        return BaseResp.success();
    }
}