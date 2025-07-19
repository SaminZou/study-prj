package com.samin.mybatis.controller;

import com.samin.mybatis.model.UserVO;
import com.samin.mybatis.service.UserService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
public class UserController {

    private final UserService userService;

    @PostMapping("/user/list")
    public List<UserVO> list() {
        return userService.findAll();
    }

    @PostMapping("/user/direct/list")
    public List<UserVO> directList() {
        return userService.directList();
    }

    @PostMapping("/user/custom/list")
    public List<UserVO> customList() {
        return userService.customList();
    }
}
