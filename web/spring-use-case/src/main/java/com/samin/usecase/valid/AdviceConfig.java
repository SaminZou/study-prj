package com.samin.usecase.valid;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import javax.validation.ConstraintViolationException;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AdviceConfig {

    @ExceptionHandler({ConstraintViolationException.class, MethodArgumentNotValidException.class})
    public String validationExceptionHandler(Exception e) {
        // 使用 Set 去重错误信息
        Set<String> errorMessages = new LinkedHashSet<>();

        // 处理 ConstraintViolationException
        if (e instanceof ConstraintViolationException) {
            ConstraintViolationException constraintViolationException = (ConstraintViolationException) e;
            errorMessages.addAll(constraintViolationException.getConstraintViolations()
                                                             .stream()
                                                             .map(violation -> String.format(
                                                                     "字段名：%s，报错信息：%s，默认值：%s",
                                                                     violation.getPropertyPath()
                                                                              .toString(), violation.getMessage(),
                                                                     violation.getInvalidValue() != null
                                                                     ? violation.getInvalidValue() : "null"))
                                                             .collect(Collectors.toSet()));
        }

        // 处理 MethodArgumentNotValidException
        if (e instanceof MethodArgumentNotValidException) {
            MethodArgumentNotValidException methodArgumentNotValidException = (MethodArgumentNotValidException) e;
            errorMessages.addAll(methodArgumentNotValidException.getBindingResult()
                                                                .getFieldErrors()
                                                                .stream()
                                                                .map(error -> String.format(
                                                                        "字段名：%s，报错信息：%s，默认值：%s", error.getField(),
                                                                        error.getDefaultMessage(),
                                                                        error.getRejectedValue() != null
                                                                        ? error.getRejectedValue() : "null"))
                                                                .collect(Collectors.toSet()));
        }

        return String.join("; ", errorMessages);
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
