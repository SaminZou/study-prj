package com.samin.wx.model;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class GetPhoneNumberReq {

    @NotBlank(message = "code不能为空")
    @Size(max = 255, message = "code长度不能超过255个字符")
    private String code;
}
