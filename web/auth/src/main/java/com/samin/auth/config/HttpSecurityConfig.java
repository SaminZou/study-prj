package com.samin.auth.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Spring Boot Security 配置类
 *
 * @author samin
 * @date 2022-08-07
 */
@Slf4j
@Configuration
public class HttpSecurityConfig extends WebSecurityConfigurerAdapter  {

    @Override
    public void configure(WebSecurity web) {
        web.ignoring()
                .antMatchers(HttpMethod.OPTIONS, "/**")
                // 认证授权
                .antMatchers("/login")
                .antMatchers("/logout");
    }
}