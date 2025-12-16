package com.samin.wx.config;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.api.impl.WxMaServiceImpl;
import cn.binarywang.wx.miniapp.config.impl.WxMaDefaultConfigImpl;
import com.samin.wx.common.BusinessException;
import com.samin.wx.common.ResultCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class WxConfig {

    private final WxMiniProperties wxMiniProperties;

    @Bean
    public WxMaService wxMaService() {
        // 验证配置是否有效
        if (wxMiniProperties.getAppid().equals("0") || wxMiniProperties.getSecret().equals("0")) {
            log.error("微信小程序配置无效，请检查appid和secret");
            throw new BusinessException(ResultCode.CONFIG_ERROR, "微信小程序配置无效，请检查appid和secret");
        }

        WxMaDefaultConfigImpl config = new WxMaDefaultConfigImpl();
        config.setAppid(wxMiniProperties.getAppid());
        config.setSecret(wxMiniProperties.getSecret());
        WxMaService service = new WxMaServiceImpl();
        service.setWxMaConfig(config);

        log.info("微信小程序初始化成功，appid：{}", wxMiniProperties.getAppid().substring(0, 8) + "****");
        return service;
    }
}