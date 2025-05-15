package com.samin.wx.controller;


import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


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
public class WxMiniController {

    private final WxMaService wxMaService;

    @GetMapping("/login")
    public void login(@RequestParam String code) throws WxErrorException {
        WxMaJscode2SessionResult session = wxMaService.getUserService()
                                                      .getSessionInfo(code);

        log.info("获取 open_id 成功：{}", session.toString());
    }
}