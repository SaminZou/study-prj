package com.samin.auth.service;

import com.samin.auth.authentication.CustomUserDetails;
import com.samin.auth.vo.resp.BaseInfoResp;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class BaseInfoService {

    private final SecurityService securityService;

    public BaseInfoResp baseInfo() {
        CustomUserDetails currentUser = securityService.getCurrentUser();

        BaseInfoResp baseInfoResp = new BaseInfoResp();
        baseInfoResp.setRoles(currentUser.getRoles());
        baseInfoResp.setMenus(currentUser.getMenus());
        baseInfoResp.setResources(currentUser.getResources());

        return baseInfoResp;
    }
}
