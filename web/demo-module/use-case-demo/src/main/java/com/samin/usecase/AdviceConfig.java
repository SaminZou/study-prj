package com.samin.usecase;

import java.util.List;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AdviceConfig {

    /**
     * 参数校验失败
     */
    @ExceptionHandler(BindException.class)
    public String bindExceptionHandler(BindException ex) {
        List<FieldError> fieldErrors = ex.getFieldErrors();

        StringBuilder sb = new StringBuilder();
        for (FieldError fieldError : fieldErrors) {
            sb.append("[");
            sb.append(fieldError.getField());
            sb.append("]: ");
            sb.append(fieldError.getDefaultMessage());
            sb.append("\n");
        }

        return sb.toString();
    }
}
