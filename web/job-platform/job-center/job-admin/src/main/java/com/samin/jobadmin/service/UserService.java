package com.samin.jobadmin.service;

import com.samin.jobadmin.bean.UserVo;
import com.samin.jobadmin.entity.User;
import com.samin.jobadmin.exception.BusException;
import com.samin.jobadmin.exception.ExceptionEnums;
import com.samin.jobadmin.repository.UserRepository;
import com.samin.jobsdk.bean.PageReq;
import com.samin.jobsdk.bean.PageResp;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public PageResp<UserVo> page(PageReq<Void> req) {
        Sort sort = Sort.by(Sort.Direction.DESC, "createTime");
        Pageable pageable = PageRequest.of(req.getPage(), req.getSize(), sort);
        PageResp<User> users = PageResp.success(userRepository.findAll(pageable));

        return users.map(UserVo::getInstance);
    }

    public void disable(Integer id) {
        Optional<User> userOptional = getUser(id);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            user.setIsEnable(0);
            user.setUpdateTime(LocalDateTime.now());
            userRepository.save(user);
        } else {
            ExceptionEnums.throwException(ExceptionEnums.USER_NOT_EXIST_ERROR);
        }
    }

    private Optional<User> getUser(Integer id) throws BusException {
        return userRepository.findById(id);
    }
}