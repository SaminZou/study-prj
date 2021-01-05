package basic.q7.annotaion;

import java.lang.annotation.*;

/**
 * 字段映射
 *
 * @author samin
 * @date 2020-12-22
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface Column {

    String column() default "";

    boolean require() default false;
}
