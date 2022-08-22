package com.samin.jpademo.controller;

import com.samin.jpademo.entity.User;
import com.samin.jpademo.service.UserRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class UserController {

    private final UserRepository userRepository;

    @PostMapping("/user/list")
    public List<User> list() {
        return userRepository.findAll();
    }

    @PostMapping("/user/update")
    public User save(@RequestBody User user) {
        return userRepository.save(user);
    }

    @PostMapping("/user/delete/{id}")
    public void delete(@PathVariable("id") Integer id) {
        userRepository.deleteById(id);
    }
}
