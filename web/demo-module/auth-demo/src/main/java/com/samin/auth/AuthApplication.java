package com.samin.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

/**
 * @author samin
 * @Securitd，@RolesAllowed，@PreAuthorize，@PostAuthorize
 * @Securitd，@RolesAllowed 和一样，是通用标准
 * @PreAuthorize，@PostAuthorize 是 spring security 的定制注解，可以使用 SEL 表达式
 * @date 2023-07-30
 */
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
@SpringBootApplication
public class AuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuthApplication.class, args);
    }
}
