package com.samin.auth.service;

import com.samin.auth.vo.req.UserSaveReq;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

@Slf4j
@SpringBootTest
class UserServiceTest {

    @Autowired
    private UserService userService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    void getPassword() {
        log.info("{}", passwordEncoder.encode("123456"));
    }

    // @Test
    void initUser() {
        // 初始用户
        UserSaveReq user = new UserSaveReq();
        user.setMobile("13800000000");
        user.setPassword("123456");
        user.setIcon(null);
        user.setEmail("822085977@qq.com");
        user.setNickName("samin");
        user.setNote("初始用户");
        userService.saveUser(user);
    }
}