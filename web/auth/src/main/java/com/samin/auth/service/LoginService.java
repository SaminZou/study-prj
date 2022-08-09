package com.samin.auth.service;

import com.samin.auth.authentication.CustomAuthenticationToken;
import com.samin.auth.authentication.CustomUserDetails;
import java.util.HashMap;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class LoginService {

    @Resource
    private AuthenticationManager authenticationManager;

    public HashMap<String, String> login(HashMap<String, String> loginReq) {
        Authentication authenticate = authenticationManager.authenticate(
                new CustomAuthenticationToken(loginReq.get("name"), loginReq.get("pwd")));

        CustomUserDetails dmpUserDetails = (CustomUserDetails) authenticate.getPrincipal();
        log.info("登录成功：{}", dmpUserDetails);


        return new HashMap<>(1) {
            {
                put("msg", "登录成功");
            }
        };
    }
}
