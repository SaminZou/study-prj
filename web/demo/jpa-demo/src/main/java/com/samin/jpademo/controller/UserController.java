package com.samin.jpademo.controller;

import com.samin.jpademo.entity.User;
import com.samin.jpademo.service.UserService;
import java.util.List;
import lombok.RequiredArgsConstructor;
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
@RequiredArgsConstructor
@RestController
public class UserController {

    private final UserService userService;

    @PostMapping("/user/list")
    public List<User> list() {
        return userService.findAll();
    }

    @PostMapping("/user/update")
    public User save(@RequestBody User user) {
        return userService.save(user);
    }

    @GetMapping("/user/findbysex")
    public List<User> findBySex(@RequestParam(value = "sex") int sex) {
        return userService.findBySex(sex);
    }

    @PostMapping("/user/delete/{id}")
    public void delete(@PathVariable("id") Integer id) {
        userService.deleteById(id);
    }
}