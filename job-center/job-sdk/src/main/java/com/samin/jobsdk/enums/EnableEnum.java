package com.samin.jobsdk.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum EnableEnum {

    UNKNOWN(-1, "未知状态"),
    ENABLE(1, "启用"),
    DISABLE(0, "禁用");

    private final int code;
    private final String value;

    public static EnableEnum parseByCode(int code) {
        for (EnableEnum e : EnableEnum.values()) {
            if (code == e.getCode()) {
                return e;
            }
        }

        return UNKNOWN;
    }
}
