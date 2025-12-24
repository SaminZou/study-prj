package com.samin.mybatis.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.samin.mybatis.model.PageReq;
import com.samin.mybatis.model.UserQueryVO;
import com.samin.mybatis.model.UserVO;
import com.samin.mybatis.model.dto.Result;
import com.samin.mybatis.po.UserPO;
import com.samin.mybatis.service.UserService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import javax.validation.Valid;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @DeleteMapping("/{id}")
    public Result<Void> delete(@PathVariable Integer id) {
        userService.delete(id);
        return Result.success();
    }

    @PostMapping
    public Result<UserPO> create(@RequestBody @Valid UserVO req) {
        return Result.success(userService.insert(req));
    }

    @PutMapping
    public Result<UserPO> update(@RequestBody @Valid UserVO req) {
        return Result.success(userService.update(req));
    }

    @GetMapping("/count")
    public Result<Integer> count(@RequestBody UserQueryVO req) {
        return Result.success(userService.count(req));
    }

    @GetMapping("/page/lambda")
    public Result<Page<UserPO>> pageByLambda(@RequestBody @Valid PageReq req) {
        return Result.success(userService.pageByLambda(req));
    }

    @GetMapping("/page/sql")
    public Result<Page<UserVO>> pageBySql(@RequestBody @Valid PageReq req) {
        return Result.success(userService.pageBySql(req));
    }

    @GetMapping("/page")
    public Result<Page<UserPO>> page(@RequestBody @Valid PageReq req) {
        return Result.success(userService.page(req));
    }

    

    @GetMapping("/queryByName")
    public Result<List<UserVO>> queryByName(@RequestParam String name) {
        return Result.success(userService.queryByName(name));
    }

    @GetMapping("/names")
    public Result<List<String>> names() {
        return Result.success(userService.names());
    }

    

    @GetMapping("/direct")
    public Result<List<UserVO>> directList() {
        return Result.success(userService.directList());
    }

    @GetMapping("/custom")
    public Result<List<UserVO>> customList() {
        return Result.success(userService.customList());
    }
}
