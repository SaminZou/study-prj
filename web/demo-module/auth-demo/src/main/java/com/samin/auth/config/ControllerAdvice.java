package com.samin.auth.config;

import com.samin.auth.exception.BusException;
import com.samin.auth.vo.base.BaseResp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 统一错误抛出
 *
 * @author samin
 * @date 2022-10-28
 */
@Slf4j
@RestControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public BaseResp<Void> exception(Exception e) {
        log.error("系统内部错误：", e);
        return BaseResp.fail(-1, e.getMessage());
    }

    @ExceptionHandler(BusException.class)
    @ResponseStatus(HttpStatus.OK)
    public BaseResp<Void> runtimeException(BusException e) {
        log.error("BusException：", e);
        return BaseResp.fail(e.getCode(), e.getMessage());
    }
}
