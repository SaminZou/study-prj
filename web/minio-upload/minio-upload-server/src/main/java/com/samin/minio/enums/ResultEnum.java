package com.samin.minio.enums;

public enum ResultEnum {

    SUCCESS(200000, "成功"),
    ERROR_400(400000, "参数错误"),
    ERROR_400100(400100, "用户名或者密码错误"),
    ERROR_401(401000, "token失效，请重新登录"),
    ERROR_403(403000, "未授权的访问"),
    ERROR_404(404000, "请求的网页/路径不存在"),
    ERROR_500(500000, "系统错误:请联系管理员");

    private Integer code;
    private String msg;

    ResultEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
