package com.samin.dify.model;

import lombok.Data;

/**
 * 统一响应结果
 *
 * @param <T> 响应数据类型
 */
@Data
public class ResponseResult<T> {

    /**
     * 响应码：200 表示成功，其他表示失败
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

    public ResponseResult() {
    }

    public ResponseResult(Integer code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    /**
     * 成功响应
     */
    public static <T> ResponseResult<T> success(T data) {
        return new ResponseResult<>(200, "success", data);
    }

    /**
     * 成功响应（无数据）
     */
    public static <T> ResponseResult<T> success() {
        return new ResponseResult<>(200, "success", null);
    }

    /**
     * 失败响应
     */
    public static <T> ResponseResult<T> error(String message) {
        return new ResponseResult<>(500, message, null);
    }

    /**
     * 失败响应（自定义错误码）
     */
    public static <T> ResponseResult<T> error(Integer code, String message) {
        return new ResponseResult<>(code, message, null);
    }
}



