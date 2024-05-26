package com.samin.jobadmin.service;

import com.samin.jobadmin.bean.UserVo;
import com.samin.jobsdk.bean.PageReq;
import com.samin.jobsdk.bean.PageResp;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    public PageResp<UserVo> page(PageReq<Void> req) {
        return null;
    }
}