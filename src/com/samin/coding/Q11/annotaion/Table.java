package com.samin.coding.Q11.annotaion;

import java.lang.annotation.*;

/**
 * 表名映射
 *
 * @author samin
 * @date 2020-12-22
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface Table {

    String tableName() default "";
}
