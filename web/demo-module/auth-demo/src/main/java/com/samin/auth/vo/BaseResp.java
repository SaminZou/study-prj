package com.samin.auth.vo;

import lombok.Data;

@Data
public class BaseResp<T> {

    private int code;
    private String msg;
    private T data;

    public static <T> BaseResp<T> success(T data) {
        BaseResp<T> resp = new BaseResp<>();
        resp.setCode(0);
        resp.setMsg("success");
        resp.setData(data);
        return resp;
    }

    public static <T> BaseResp<T> fail(int code, String msg) {
        BaseResp<T> resp = new BaseResp<>();
        resp.setCode(code);
        resp.setMsg(msg);
        resp.setData(null);
        return resp;
    }
}
