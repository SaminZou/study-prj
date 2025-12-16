package com.samin.wx.config;

import com.samin.wx.common.BusinessException;
import com.samin.wx.common.Result;
import com.samin.wx.common.ResultCode;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.stream.Collectors;

@Slf4j
@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {

    /**
     * 处理业务异常
     */
    @ExceptionHandler(BusinessException.class)
    public Result<Void> handleBusinessException(BusinessException e) {
        log.warn("Business exception: code={}, message={}", e.getCode(), e.getMessage(), e);
        return Result.fail(e.getCode(), e.getMessage());
    }

    /**
     * 处理微信API异常
     */
    @ExceptionHandler(WxErrorException.class)
    public Result<Void> handleWxErrorException(WxErrorException e) {
        log.error("WeChat API error: {}", e.getMessage(), e);
        return Result.fail(ResultCode.SYSTEM_ERROR, "微信服务异常：" + e.getMessage());
    }

    /**
     * 处理参数验证异常
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Result<Void> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        BindingResult bindingResult = e.getBindingResult();
        String errorMessage = bindingResult.getFieldErrors()
                .stream()
                .map(FieldError::getDefaultMessage)
                .collect(Collectors.joining(", "));
        
        log.warn("Parameter validation failed: {}", errorMessage, e);
        return Result.fail(ResultCode.PARAM_ERROR, errorMessage);
    }

    /**
     * 处理其他所有异常
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Result<Void> handleException(Exception e) {
        log.error("System error: {}", e.getMessage(), e);
        return Result.fail(ResultCode.SYSTEM_ERROR, "系统异常，请稍后重试");
    }
}