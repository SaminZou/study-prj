package com.samin.mybatis.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.samin.mybatis.model.PageReq;
import com.samin.mybatis.model.UserQueryVO;
import com.samin.mybatis.model.UserVO;
import com.samin.mybatis.po.UserPO;
import com.samin.mybatis.service.UserService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
public class UserController {

    private final UserService userService;

    @PostMapping("/user/insert")
    public UserPO insert(@RequestBody UserVO req) {
        return userService.insert(req);
    }

    @PostMapping("/user/update")
    public UserPO update(@RequestBody UserVO req) {
        return userService.update(req);
    }

    @PostMapping("/user/count")
    public Integer count(@RequestBody UserQueryVO req) {
        return userService.count(req);
    }

    @PostMapping("/user/page/lambda")
    public Page<UserPO> pageByLambda(@RequestBody PageReq req) {
        return userService.pageByLambda(req);
    }

    @PostMapping("/user/page/sql")
    public Page<UserVO> pageBySql(@RequestBody PageReq req) {
        return userService.pageBySql(req);
    }

    @PostMapping("/user/page")
    public Page<UserPO> page(@RequestBody PageReq req) {
        return userService.page(req);
    }

    @PostMapping("/user/list")
    public List<UserVO> list() {
        return userService.findAll();
    }

    @PostMapping("/user/queryByName")
    public List<UserVO> queryByName(@RequestParam String name) {
        return userService.queryByName(name);
    }

    @PostMapping("/user/query/list")
    public List<UserVO> queryList(@RequestBody UserQueryVO req) {
        return userService.queryList(req);
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
