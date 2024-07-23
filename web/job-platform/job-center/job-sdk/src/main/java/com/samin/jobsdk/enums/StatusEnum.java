package com.samin.jobsdk.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum StatusEnum {

    UNKNOWN(-1, "未知状态"),
    ONLINE(1, "在线"),
    OFFLINE(0, "离线");

    private final int code;
    private final String value;

    public static StatusEnum parseByCode(int code) {
        for (StatusEnum e : StatusEnum.values()) {
            if (code == e.getCode()) {
                return e;
            }
        }

        return UNKNOWN;
    }
}
