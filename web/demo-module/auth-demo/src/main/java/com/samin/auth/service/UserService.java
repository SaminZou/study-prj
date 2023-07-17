package com.samin.auth.service;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import cn.hutool.core.util.StrUtil;
import com.samin.auth.entity.User;
import com.samin.auth.exception.ExceptionEnums;
import com.samin.auth.repo.UserRepository;
import com.samin.auth.vo.UserVo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserVo saveUser(UserVo userVo) {
        User user;
        // update
        if (Objects.nonNull(userVo.getId())) {
            Optional<User> userOptional = userRepository.findById(userVo.getId());

            if (userOptional.isPresent()) {
                user = userOptional.get();
                CopyOptions options = CopyOptions.create().ignoreNullValue().setIgnoreProperties("nickName");
                BeanUtil.copyProperties(userVo, user, options);

                if (StrUtil.isNotBlank(user.getPassword())) {
                    user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
                }

                userRepository.save(user);
            }

            // insert
        } else {
            Optional<User> userOptional = userRepository.findByNickName(userVo.getNickName());

            if (userOptional.isPresent()) {
                ExceptionEnums.throwException(ExceptionEnums.USER_EXIST_ERROR);
            }

            user = new User();
            CopyOptions options = CopyOptions.create().ignoreNullValue();
            BeanUtil.copyProperties(userVo, user, options);

            if (StrUtil.isNotBlank(user.getPassword())) {
                user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
            }

            userRepository.save(user);

            userVo.setId(user.getId());
        }

        return userVo;
    }
}
