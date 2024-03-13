package com.samin.auth.authentication;

import com.samin.auth.common.Log;
import com.samin.auth.service.LoginService;
import com.samin.auth.vo.base.BaseResp;
import com.samin.auth.vo.req.LoginReq;
import com.samin.auth.vo.resp.UserInfoResp;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
@Api("登录控制器")
@RestController
@RequiredArgsConstructor
public class LoginController {

    private final LoginService loginService;

    @Log("登录")
    @ApiOperation("登录接口")
    @PostMapping(value = "/login")
    public BaseResp<String> login(@RequestBody LoginReq loginReq) {
        return BaseResp.success(loginService.login(loginReq));
    }

    @ApiOperation("用户信息控制器")
    @PostMapping(value = "/user_info")
    public BaseResp<UserInfoResp> userInfo() {
        return BaseResp.success(loginService.userInfo());
    }
}