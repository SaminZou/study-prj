package com.samin.idempotent.itf;

import java.lang.annotation.*;
import java.util.concurrent.TimeUnit;

@Target({ElementType.METHOD, ElementType.PARAMETER, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface RequestLock {

    String delimiter() default "&";

    String prefix() default "";

    long expire() default 1000;

    TimeUnit timeUnit() default TimeUnit.MILLISECONDS;
}