package com.samin.minio.exception;

import com.samin.minio.enums.ResultEnum;

/**
 * 业务异常类
 *
 * @since 2024-12-17
 */
public class BusinessException extends RuntimeException {

    private Integer code;
    private String message;

    public BusinessException(ResultEnum resultEnum) {
        super(resultEnum.getMsg());
        this.code = resultEnum.getCode();
        this.message = resultEnum.getMsg();
    }

    public BusinessException(ResultEnum resultEnum, String message) {
        super(message);
        this.code = resultEnum.getCode();
        this.message = message;
    }

    public BusinessException(Integer code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}