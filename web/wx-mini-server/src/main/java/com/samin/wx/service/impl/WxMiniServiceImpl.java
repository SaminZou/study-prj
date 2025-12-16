package com.samin.wx.service.impl;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import cn.binarywang.wx.miniapp.bean.WxMaPhoneNumberInfo;
import com.samin.wx.common.BusinessException;
import com.samin.wx.common.Constants;
import com.samin.wx.common.ResultCode;
import com.samin.wx.service.WxMiniService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class WxMiniServiceImpl implements WxMiniService {

    private final WxMaService wxMaService;

    @Override
    public WxMaJscode2SessionResult login(String code) throws WxErrorException {
        try {
            log.info(Constants.LOG_REQUEST_CODE_LENGTH, code.length());
            WxMaJscode2SessionResult session = wxMaService.getUserService().getSessionInfo(code);
            log.info(Constants.LOG_WX_LOGIN_SUCCESS);
            return session;
        } catch (WxErrorException e) {
            log.error(Constants.LOG_WX_LOGIN_FAILED, e.getError().getErrorCode());
            throw new BusinessException(ResultCode.WX_LOGIN_FAILED, "微信登录失败：" + e.getError().getErrorMsg());
        }
    }

    @Override
    public WxMaPhoneNumberInfo getPhoneNumber(String code) throws WxErrorException {
        try {
            log.info(Constants.LOG_REQUEST_CODE_LENGTH, code.length());
            WxMaPhoneNumberInfo phoneNumberInfo = wxMaService.getUserService().getPhoneNoInfo(code);
            log.info(Constants.LOG_GET_PHONE_SUCCESS);
            return phoneNumberInfo;
        } catch (WxErrorException e) {
            log.error(Constants.LOG_GET_PHONE_FAILED, e.getError().getErrorCode());
            throw new BusinessException(ResultCode.GET_PHONE_NUMBER_FAILED, "获取手机号失败：" + e.getError().getErrorMsg());
        }
    }
}