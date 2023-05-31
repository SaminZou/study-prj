package com.samin.auth.exception;

/**
 * 统一业务错误类
 *
 * @author samin
 * @date 2022-08-18
 */
public class BusException extends RuntimeException {

    private int code;

    public int getCode() {
        return code;
    }

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
