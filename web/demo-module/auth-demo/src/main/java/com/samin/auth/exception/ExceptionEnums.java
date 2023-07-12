package com.samin.auth.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ExceptionEnums implements BusinessCode {

    UNKNOWN(20000, "未知错误"),
    SYSTEM_ERROR(20001, "系统异常"),
    USER_NOT_EXIST_ERROR(20002, "用户不存在"),
    ;

    private final int code;
    private final String value;

    public static ExceptionEnums parseByCode(int code) {
        for (ExceptionEnums e : ExceptionEnums.values()) {
            if (code == e.getCode()) {
                return e;
            }
        }

        return UNKNOWN;
    }

    public static void throwException(ExceptionEnums exceptionEnums) throws BusException {
        throw new BusException(exceptionEnums);
    }
}
