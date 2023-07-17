package com.samin.auth.service;


import com.samin.auth.authentication.CustomUserDetails;
import com.samin.auth.entity.User;
import com.samin.auth.exception.ExceptionEnums;
import com.samin.auth.repo.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * 自定义用户信息加载服务类
 *
 * @author samin
 * @date 2022-08-08
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class CustomUserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) {
        Optional<User> userOptional = userRepository.findByNickName(username);
        if (!userOptional.isPresent()) {
            ExceptionEnums.throwException(ExceptionEnums.USER_NOT_EXIST_ERROR);
        }

        return CustomUserDetails.getInstance(userOptional.get());
    }
}
