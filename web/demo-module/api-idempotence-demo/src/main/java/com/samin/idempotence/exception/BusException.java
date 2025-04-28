package com.samin.idempotence.exception;

import lombok.Getter;

/**
 * 统一业务错误类
 *
 * @author samin
 * @date 2022-08-18
 */
@Getter
public class BusException extends RuntimeException {

    private final int code;

    public BusException(int code) {
        this.code = code;
    }

    public BusException(int code, String msg) {
        super(msg);
        this.code = code;
    }

    public BusException(int code, String msg, Throwable cause) {
        super(msg, cause);
        this.code = code;
    }

    public BusException(BusinessCode businessCode) {
        super(businessCode.getValue());
        this.code = businessCode.getCode();
    }
}
