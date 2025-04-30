package com.samin.auth.authentication;

import com.samin.auth.service.CustomUserDetailsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class CustomAuthenticationProvider implements AuthenticationProvider {

    private final CustomUserDetailsService customUserDetailsService;
    private final CustomUserDetailsChecker customUserDetailsChecker;

    @Bean
    public CustomAuthenticationProvider dmpAuthenticationProvider() {
        return new CustomAuthenticationProvider(customUserDetailsService, customUserDetailsChecker);
    }

    /**
     * 自定义认证逻辑
     *
     * @param authentication 认证信息
     * @return 认证结果
     * @throws AuthenticationException
     */
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        CustomAuthenticationToken request = (CustomAuthenticationToken) authentication;

        UserDetails userDetails = customUserDetailsService.loadUserByUsername(request.getUsername());

        // 前置认证
        customUserDetailsChecker.check(userDetails);

        // 密码加密后比较是否匹配
        if (!bCryptPasswordEncoder().matches(request.getPassword(), userDetails.getPassword())) {
            log.info("账号或密码错误");
            throw new BadCredentialsException("账号或密码错误");
        }

        return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
    }

    /**
     * 自定义认证 Token
     *
     * @param authentication
     * @return
     */
    @Override
    public boolean supports(Class<?> authentication) {
        return (CustomAuthenticationToken.class.isAssignableFrom(authentication));
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
