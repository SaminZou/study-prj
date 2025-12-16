package com.samin.wx.service;

import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import cn.binarywang.wx.miniapp.bean.WxMaPhoneNumberInfo;
import me.chanjar.weixin.common.error.WxErrorException;

public interface WxMiniService {

    /**
     * 微信登录，获取session信息
     * @param code 微信小程序登录code
     * @return session信息
     * @throws WxErrorException 微信API异常
     */
    WxMaJscode2SessionResult login(String code) throws WxErrorException;

    /**
     * 获取用户手机号
     * @param code 手机号授权code
     * @return 手机号信息
     * @throws WxErrorException 微信API异常
     */
    WxMaPhoneNumberInfo getPhoneNumber(String code) throws WxErrorException;
}