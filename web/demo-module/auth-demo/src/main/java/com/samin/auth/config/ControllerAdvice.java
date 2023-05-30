package com.samin.auth.config;

import com.samin.auth.exception.DmpBusException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;

/**
 * 统一错误抛出
 *
 * @author samin
 * @date 2022-10-28
 */
@Slf4j
@RestControllerAdvice
public class ControllerAdvice {

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public HashMap<String, String> exception(Exception e) {
        log.error("系统内部错误：", e);
        return new HashMap<String, String>(1) {
            {
                put("msg", e.getMessage());
            }
        };
    }

    @ExceptionHandler(DmpBusException.class)
    @ResponseStatus(HttpStatus.OK)
    public HashMap<String, String> lingyunRuntimeException(DmpBusException e) {
        log.error("DmpBusException：", e);
        return new HashMap<String, String>(1) {
            {
                put("msg", e.getMessage());
            }
        };
    }
}
