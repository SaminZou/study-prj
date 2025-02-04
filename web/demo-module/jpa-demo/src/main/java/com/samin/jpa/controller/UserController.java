package com.samin.jpa.controller;

import com.samin.jpa.entity.UserVO;
import com.samin.jpa.service.UserService;
import java.util.List;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户控制类
 *
 * @author samin
 * @date 2022-11-23
 */
@Slf4j
@RequiredArgsConstructor
@RestController
public class UserController {

    private final UserService userService;

    @PostMapping("/user/save/all")
    public void saveAll() {
        userService.saveAll();
    }

    @PostMapping("/user/distinct")
    public List<Integer> distinct() {
        return userService.distinct();
    }

    @PostMapping("/user/between")
    public List<UserVO> between() {
        return userService.between();
    }

    @PostMapping("/user/count")
    public List<Long> count() {
        return userService.count();
    }

    @PostMapping("/user/list")
    public List<UserVO> list() {
        return userService.findAll();
    }

    @PostMapping("/user/update")
    public UserVO save(@Valid @RequestBody UserVO userDO) {
        log.info("保存时间：{}", userDO.getDate());
        return userService.saveUser(userDO);
    }

    @GetMapping("/user/{id}")
    public UserVO detail(@PathVariable(value = "id") Integer id) {
        return userService.detail(id);
    }

    @GetMapping("/user/findbysex")
    public List<UserVO> findBySex(@RequestParam(value = "sex") int sex) {
        return userService.findBySex(sex);
    }

    @PostMapping("/user/delete/{id}")
    public void delete(@PathVariable("id") Integer id) {
        userService.deleteById(id);
    }

    @PostMapping("/user/remove/{sex}")
    public void remove(@PathVariable("sex") Integer sex) {
        userService.deleteBySex(sex);
    }
}
