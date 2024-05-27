package com.samin.jobadmin.service;

import com.samin.jobadmin.bean.UserVo;
import com.samin.jobadmin.entity.User;
import com.samin.jobadmin.repository.UserRepository;
import com.samin.jobsdk.bean.PageReq;
import com.samin.jobsdk.bean.PageResp;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

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
}