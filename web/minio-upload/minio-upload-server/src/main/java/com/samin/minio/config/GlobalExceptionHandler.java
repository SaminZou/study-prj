package com.samin.minio.config;

import com.samin.minio.enums.ResultEnum;
import com.samin.minio.exception.BusinessException;
import com.samin.minio.model.dto.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;

/**
 * 全局异常处理类
 *
 * @since 2024-12-17
 */
@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * 处理自定义业务异常
     */
    @ExceptionHandler(BusinessException.class)
    public Result handleBusinessException(BusinessException e, HttpServletRequest request) {
        logger.error("业务异常: {}", e.getMessage(), e);
        return Result.error(e.getCode(), e.getMessage());
    }

    /**
     * 处理参数验证异常
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result handleValidationException(MethodArgumentNotValidException e, HttpServletRequest request) {
        logger.error("参数验证异常: {}", e.getMessage(), e);
        String errorMsg = e.getBindingResult().getFieldError().getDefaultMessage();
        return Result.error(ResultEnum.ERROR_400.getCode(), errorMsg);
    }

    /**
     * 处理 404 异常
     */
    @ExceptionHandler(NoHandlerFoundException.class)
    public Result handleNotFoundException(NoHandlerFoundException e, HttpServletRequest request) {
        logger.error("404异常: {}", e.getMessage(), e);
        return Result.error(ResultEnum.ERROR_404.getCode(), ResultEnum.ERROR_404.getMsg());
    }

    /**
     * 处理其他所有异常
     */
    @ExceptionHandler(Exception.class)
    public Result handleException(Exception e, HttpServletRequest request) {
        logger.error("系统异常: {}", e.getMessage(), e);
        return Result.error(ResultEnum.ERROR_500.getCode(), ResultEnum.ERROR_500.getMsg());
    }
}