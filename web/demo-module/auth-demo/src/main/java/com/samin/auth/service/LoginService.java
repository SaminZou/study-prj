package com.samin.auth.service;

import com.samin.auth.authentication.CustomAuthenticationToken;
import com.samin.auth.authentication.CustomUserDetails;
import com.samin.auth.util.JwtUtil;
import com.samin.auth.vo.base.BaseResp;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RBucket;
import org.redisson.api.RedissonClient;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.HashMap;
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

    private final AuthenticationManager authenticationManager;
    private final RedissonClient redissonClient;
    private final JwtUtil jwtUtil;

    public BaseResp<String> login(HashMap<String, String> loginReq) {
        Authentication authenticate = authenticationManager.authenticate(new CustomAuthenticationToken(loginReq.get("name"), loginReq.get("pwd")));

        CustomUserDetails userDetails = (CustomUserDetails) authenticate.getPrincipal();
        // save token
        RBucket<CustomUserDetails> userDetailsRedisBucket = redissonClient.getBucket("token:" + userDetails.getUser()
                .getId());
        userDetailsRedisBucket.set(userDetails, jwtUtil.getExpiration(), TimeUnit.SECONDS);
        log.info("登录成功：{}", userDetails);

        return BaseResp.success(jwtUtil.generateToken(userDetails));
    }
}