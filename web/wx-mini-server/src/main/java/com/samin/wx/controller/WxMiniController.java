package com.samin.wx.controller;

import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import cn.binarywang.wx.miniapp.bean.WxMaPhoneNumberInfo;
import com.samin.wx.common.Result;
import com.samin.wx.model.GetPhoneNumberReq;
import com.samin.wx.service.WxMiniService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;

/**
 * 微信登录
 * <p>
 * Description: 微信登录
 * <p>
 * Created By: Samin Created Date: 2025-05-13
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/wx")
@Validated
public class WxMiniController {

    private final WxMiniService wxMiniService;

    @GetMapping("/login")
    public Result<WxMaJscode2SessionResult> login(@RequestParam @NotBlank(message = "code不能为空") String code) throws WxErrorException {
        WxMaJscode2SessionResult session = wxMiniService.login(code);
        return Result.success(session);
    }

    @PostMapping("/getPhoneNumber")
    public Result<WxMaPhoneNumberInfo> getPhoneNumber(@RequestBody @Validated GetPhoneNumberReq req) throws WxErrorException {
        WxMaPhoneNumberInfo phoneNumberInfo = wxMiniService.getPhoneNumber(req.getCode());
        return Result.success(phoneNumberInfo);
    }
}