package com.samin.wx.common;

public enum ResultCode {
    // 成功状态码
    SUCCESS(200, "操作成功"),

    // 系统错误
    SYSTEM_ERROR(500, "系统错误"),
    PARAM_ERROR(400, "参数错误"),
    UNAUTHORIZED(401, "未授权"),
    FORBIDDEN(403, "禁止访问"),
    NOT_FOUND(404, "资源不存在"),

    // 业务错误
    WX_LOGIN_FAILED(10001, "微信登录失败"),
    GET_PHONE_NUMBER_FAILED(10002, "获取手机号失败"),
    INVALID_CODE(10003, "无效的code"),
    CONFIG_ERROR(10004, "配置错误");

    private final int code;
    private final String message;

    ResultCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}