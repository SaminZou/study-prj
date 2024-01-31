package com.samin.demo;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
// 核心是自动注入 bean
@Import({CustomLoadConfirm.class})
public @interface EnabelCustomLoadConfirm {

}
