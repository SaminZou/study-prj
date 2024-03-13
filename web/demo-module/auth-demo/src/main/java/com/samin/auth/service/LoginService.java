package com.samin.auth.service;

import com.samin.auth.authentication.CustomUserDetails;
import com.samin.auth.util.JwtUtil;
import com.samin.auth.vo.req.LoginReq;
import com.samin.auth.vo.resp.UserInfoResp;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RBucket;
import org.redisson.api.RedissonClient;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * 登录类
 *
 * @author samin
 * @date 2023-07-12
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class LoginService {

    private final SecurityService securityService;
    private final RedissonClient redissonClient;
    private final CustomUserDetailsService userDetailsService;
    private final JwtUtil jwtUtil;

    public String login(LoginReq loginReq) {
        CustomUserDetails userDetails = (CustomUserDetails) userDetailsService.loadUserByUsername(loginReq.getName());

        // 使用 Spring Security 进行认证
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null);
        SecurityContextHolder.getContext()
                .setAuthentication(authentication);

        // save token
        RBucket<CustomUserDetails> userDetailsRedisBucket = redissonClient.getBucket("token:" + userDetails.getUser()
                .getId());
        userDetailsRedisBucket.set(userDetails, jwtUtil.getExpiration(), TimeUnit.SECONDS);
        log.info("登录成功：{}", userDetails);

        return jwtUtil.generateToken(userDetails);
    }

    public UserInfoResp userInfo() {
        CustomUserDetails currentUser = securityService.getCurrentUser();

        UserInfoResp userInfoResp = new UserInfoResp();
        userInfoResp.setRoles(currentUser.getRoles());
        userInfoResp.setMenus(currentUser.getMenus());
        userInfoResp.setResources(currentUser.getResources());

        return userInfoResp;
    }
}