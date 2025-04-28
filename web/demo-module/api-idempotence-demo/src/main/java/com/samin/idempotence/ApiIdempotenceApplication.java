package com.samin.idempotence;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 * @EnableCaching 开启注解缓存功能
 * <p>
 * Description: @EnableCaching 开启注解缓存功能
 * <p>
 * Created By: Samin
 * <p>
 * Created Date: 2023-12-15
 */
@EnableCaching
@SpringBootApplication
public class ApiIdempotenceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiIdempotenceApplication.class, args);
    }
}
