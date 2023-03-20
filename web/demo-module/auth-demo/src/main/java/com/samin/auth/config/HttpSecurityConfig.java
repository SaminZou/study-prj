package com.samin.auth.config;

import com.samin.auth.authentication.CustomAuthenticationProvider;
import com.samin.auth.handler.CustomAccessDeniedHandler;
import com.samin.auth.handler.CustomAuthenticationEntryPoint;
import java.util.Collections;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.header.Header;
import org.springframework.security.web.header.writers.StaticHeadersWriter;

/**
 * Spring Boot Security 配置类
 *
 * @author samin
 * @date 2022-08-07
 */
@Slf4j
@Configuration
public class HttpSecurityConfig extends WebSecurityConfigurerAdapter {

    @Resource
    private CustomAuthenticationEntryPoint customAuthenticationEntryPoint;
    @Resource
    private CustomAccessDeniedHandler customAccessDeniedHandler;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf()
                .disable()
                .sessionManagement()
                // 关闭本地 Session
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .cors()
                .and()
                .headers()
                .addHeaderWriter(new StaticHeadersWriter(
                        Collections.singletonList(new Header("Access-Control-Expose-Headers", "Authorization"))));

        http.formLogin().disable().headers().frameOptions().disable();

        http.authorizeRequests().anyRequest().access("@permissionService.access()");

        http.exceptionHandling()
                // 认证抛错（AuthenticationProvider authentication 方法报错）
                .authenticationEntryPoint(customAuthenticationEntryPoint)
                // 鉴权拦截器
                .accessDeniedHandler(customAccessDeniedHandler);
    }

    @Override
    public void configure(WebSecurity web) {
        // 不做认证授权的地址
        web.ignoring().antMatchers(HttpMethod.OPTIONS, "/**").antMatchers("/login").antMatchers("/logout");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(dmpAuthenticationProvider());
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public CustomAuthenticationProvider dmpAuthenticationProvider() {
        return new CustomAuthenticationProvider();
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}