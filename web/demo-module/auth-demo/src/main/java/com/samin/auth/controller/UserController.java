package com.samin.auth.controller;

import com.samin.auth.common.Log;
import com.samin.auth.service.UserService;
import com.samin.auth.vo.base.BaseResp;
import com.samin.auth.vo.req.PageReq;
import com.samin.auth.vo.req.UserSaveReq;
import com.samin.auth.vo.resp.PageResp;
import com.samin.auth.vo.resp.UserResp;
import com.samin.auth.vo.resp.UserSaveResp;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.Min;
import java.io.IOException;

/**
 * 用户控制器
 * <p>
 * Description: 用户控制器
 * <p>
 * Created By: Samin
 * <p>
 * Created Date: 2023-08-25
 */
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @PostMapping("/page")
    public PageResp<UserResp> page(@RequestBody PageReq req) {
        return userService.page(req);
    }

    @PostMapping("/page/export")
    public void pageExport(@RequestBody PageReq req, HttpServletResponse response) throws IOException {
        userService.pageExport(req, response);
    }

    @Log("用户保存")
    @PostMapping("/save")
    public BaseResp<UserSaveResp> save(@RequestBody UserSaveReq userSaveReq) {
        return BaseResp.success(userService.saveUser(userSaveReq));
    }

    @PostMapping("/delete/{id}")
    public BaseResp<Void> delete(@Min(value = 1, message = "最小") @PathVariable("id") Integer id) {
        userService.delete(id);
        return BaseResp.success();
    }
}