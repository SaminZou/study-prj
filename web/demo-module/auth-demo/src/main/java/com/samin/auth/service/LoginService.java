package com.samin.auth.service;

import com.samin.auth.authentication.CustomAuthenticationToken;
import com.samin.auth.authentication.CustomUserDetails;
import com.samin.auth.vo.BaseResp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;

@Slf4j
@Service
public class LoginService {

    @Resource
    private AuthenticationManager authenticationManager;

    public BaseResp login(HashMap<String, String> loginReq) {
        Authentication authenticate = authenticationManager.authenticate(
                new CustomAuthenticationToken(loginReq.get("name"), loginReq.get("pwd")));

        CustomUserDetails dmpUserDetails = (CustomUserDetails) authenticate.getPrincipal();
        log.info("登录成功：{}", dmpUserDetails);

        return BaseResp.success(null);
    }
}
