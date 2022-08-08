package com.samin.auth.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class PermissionService {

    public boolean access() {
        log.info("access!");
        // 不做授权
        return true;
    }
}
