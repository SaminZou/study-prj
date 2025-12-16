package com.samin.wx.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;

@Data
@Component
@Validated
@ConfigurationProperties(prefix = "wx.miniapp")
public class WxMiniProperties {

    @NotBlank(message = "微信小程序appid不能为空")
    private String appid;

    @NotBlank(message = "微信小程序secret不能为空")
    private String secret;
}