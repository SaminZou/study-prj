package com.samin.auth.service;

import com.samin.auth.authentication.CustomAuthenticationToken;
import com.samin.auth.authentication.CustomUserDetails;
import com.samin.auth.common.SystemConstant;
import com.samin.auth.util.JwtUtil;
import com.samin.auth.vo.BaseResp;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.HashMap;

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
    private final JwtUtil jwtUtil;
    public static final HashMap<String, CustomUserDetails> tokenStore = new HashMap<>(100);

    public BaseResp<String> login(HashMap<String, String> loginReq) {
        Authentication authenticate = authenticationManager.authenticate(
                new CustomAuthenticationToken(loginReq.get("name"), loginReq.get("pwd")));

        CustomUserDetails userDetails = (CustomUserDetails) authenticate.getPrincipal();
        // TODO 修改为 redis
        tokenStore.put(Strings.concat(SystemConstant.LOGIN_TOKEN_CACHE_PREFIX, String.valueOf(userDetails.getUser().getId())), userDetails);
        log.info("登录成功：{}", userDetails);
        // TODO save log

        return BaseResp.success(jwtUtil.generateToken(userDetails));
    }
}
