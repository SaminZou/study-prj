package com.samin.auth.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SystemLogTypeEnum {

    LOGIN("login", "登录");

    private final String code;
    private final String label;
}
