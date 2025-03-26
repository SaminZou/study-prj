package com.samin.minio.model.dto;


import com.samin.minio.enums.ResultEnum;
import java.io.Serializable;

public class Result<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer code;

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
