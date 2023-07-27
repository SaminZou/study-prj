package com.samin.auth.service;

import com.samin.auth.authentication.CustomUserDetails;
import com.samin.auth.vo.BaseInfoVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class BaseInfoService {

    private final SecurityService securityService;

    public BaseInfoVo baseInfo() {
        CustomUserDetails currentUser = securityService.getCurrentUser();

        BaseInfoVo baseInfoVo = new BaseInfoVo();
        baseInfoVo.setRoles(currentUser.getRoles());
        baseInfoVo.setMenus(currentUser.getMenus());
        baseInfoVo.setResources(currentUser.getResources());

        return baseInfoVo;
    }
}
