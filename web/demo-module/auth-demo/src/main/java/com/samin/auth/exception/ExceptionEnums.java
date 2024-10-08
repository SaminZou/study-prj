package com.samin.auth.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ExceptionEnums implements BusinessCode {

    UNKNOWN(20000, "未知错误"),
    SYSTEM_ERROR(20001, "系统异常"),
    USER_NOT_EXIST_ERROR(20002, "用户不存在"),
    USER_EXIST_ERROR(20003, "用户已存在"),
    ID_PARSE_ERROR(20004, "jwt 中获取 id 解析错误"),
    ROLE_NOT_EXIST_ERROR(20005, "角色不存在"),
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

    public static void throwException(ExceptionEnums exceptionEnums) throws BusiException {
        throw new BusiException(exceptionEnums);
    }
}
