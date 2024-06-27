package com.samin.minio.model.dto;


import com.samin.minio.enums.ResultEnum;

import java.io.Serializable;

public class Result<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 状态码
     * <p>
     * 200000	成功
     * 400000	参数错误
     * 400100	用户名或者密码错误
     * 401000	token失效，请重新登录
     * 403000	未授权的访问
     * 404000	请求的网页/路径不存在
     * 500000	系统错误:请联系管理员
     */
    private Integer code;

    /**
     * 对应状态码的，返回提醒信息
     * <p>
     * 200000	成功
     * 400000	参数错误
     * 400100	用户名或者密码错误
     * 401000	token失效，请重新登录
     * 403000	未授权的访问
     * 404000	请求的网页/路径不存在
     * 500000	系统错误:请联系管理员
     */
    private String msg;

    /**
     * 数据对象
     */
    private T data;

    public Result() {
        this.code = ResultEnum.SUCCESS.getCode();
        this.msg = ResultEnum.SUCCESS.getMsg();
        this.data = null;
    }

    public Result(T data) {
        this.code = ResultEnum.SUCCESS.getCode();
        this.msg = ResultEnum.SUCCESS.getMsg();
        this.data = data;
    }

    public Result(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
        this.data = null;
    }

    public Result(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static Result ok() {
        return new Result();
    }

    public static <T> Result<T> ok(T data) {
        return new Result<T>(data);
    }

    public static Result error() {
        return new Result(ResultEnum.ERROR_500.getCode(), ResultEnum.ERROR_500.getMsg());
    }

    public static Result error(String msg) {
        return new Result(ResultEnum.ERROR_500.getCode(), msg);
    }

    public static Result error(Integer code, String msg) {
        return new Result(code, msg);
    }

    public static <T> Result<T> error(Integer code, String msg, T data) {
        return new Result<T>(code, msg, data);
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
