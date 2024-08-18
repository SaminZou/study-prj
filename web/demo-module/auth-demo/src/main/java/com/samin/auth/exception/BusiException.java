package com.samin.auth.exception;

import lombok.Getter;

/**
 * 统一业务错误类
 *
 * @author samin
 * @date 2022-08-18
 */
@Getter
public class BusiException extends RuntimeException {

    private final int code;

    public BusiException(int code) {
        this.code = code;
    }

    public BusiException(int code, String msg) {
        super(msg);
        this.code = code;
    }

    public BusiException(int code, String msg, Throwable cause) {
        super(msg, cause);
        this.code = code;
    }

    public BusiException(BusinessCode businessCode) {
        super(businessCode.getValue());
        this.code = businessCode.getCode();
    }
}
