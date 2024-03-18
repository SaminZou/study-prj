package com.samin.auth.config;

import com.samin.auth.handler.CustomAccessDeniedHandler;
import com.samin.auth.handler.CustomAuthenticationEntryPoint;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.header.Header;
import org.springframework.security.web.header.writers.StaticHeadersWriter;

import java.util.Collections;

/**
 * Spring Boot Security 配置类
 *
 * @author samin
 * @date 2022-08-07
 */
@Slf4j
@Configuration
@RequiredArgsConstructor
public class HttpSecurityConfig {

    private final CustomAuthenticationEntryPoint customAuthenticationEntryPoint;
    private final CustomAccessDeniedHandler customAccessDeniedHandler;

    /**
     * fix WebSecurityConfigurerAdapter @Deprecated problem
     *
     * @param http
     * @return
     * @throws Exception
     */
    @Bean
    protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf()
                .disable()
                .sessionManagement()
                // 关闭本地 Session
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .cors()
                .and()
                .headers()
                .addHeaderWriter(new StaticHeadersWriter(Collections.singletonList(new Header("Access-Control-Expose-Headers", "Authorization"))));

        http.formLogin()
                .disable()
                .headers()
                .frameOptions()
                .disable();

        http.authorizeRequests()
                .antMatchers(HttpMethod.OPTIONS, "/**")
                .permitAll()
                .antMatchers("/login")
                .permitAll()
                .antMatchers("/logout")
                .permitAll()
                //  swagger2
                .antMatchers("/doc.html", "/doc.html/**", "/webjars/**", "/v2/**", "/favicon.ico", "/swagger-resources", "/swagger-resources/**", "/swagger-ui.html", "/swagger-ui.html/**")
                .permitAll()
                // TODO temporary use
                .antMatchers("/user/save")
                .permitAll()
                .antMatchers("/api/**")
                .permitAll()
                .anyRequest()
                .access("@permissionService.access()");

        http.exceptionHandling()
                // 认证抛错（AuthenticationProvider authentication 方法报错）
                .authenticationEntryPoint(customAuthenticationEntryPoint)
                // 鉴权拦截器
                .accessDeniedHandler(customAccessDeniedHandler);

        return http.build();
    }
}