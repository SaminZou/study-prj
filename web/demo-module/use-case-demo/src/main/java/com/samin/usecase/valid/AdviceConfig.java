package com.samin.usecase.valid;

import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;
import java.util.List;

@RestControllerAdvice
public class AdviceConfig {

    /**
     * 参数校验失败
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public String constraintViolationException(ConstraintViolationException ex) {
        return ex.getMessage();
    }

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
