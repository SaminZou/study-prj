package com.samin.mybatis.model.dto;

import lombok.Data;

/**
 * 统一响应结果类
 */
@Data
public class Result<T> {

    /**
     * 响应码
     */
    private Integer code;
    /**
     * 响应消息
     */
    private String message;
    /**
     * 响应数据
     */
    private T data;

    private Result(Integer code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    /**
     * 成功响应
     */
    public static <T> Result<T> success(T data) {
        return new Result<>(200, "success", data);
    }

    /**
     * 成功响应（无数据）
     */
    public static Result<Void> success() {
        return new Result<>(200, "success", null);
    }

    /**
     * 错误响应
     */
    public static <T> Result<T> error(Integer code, String message) {
        return new Result<>(code, message, null);
    }

    /**
     * 错误响应（默认错误码）
     */
    public static <T> Result<T> error(String message) {
        return new Result<>(500, message, null);
    }
}