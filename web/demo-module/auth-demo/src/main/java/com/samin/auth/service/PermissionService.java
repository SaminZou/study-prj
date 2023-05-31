package com.samin.auth.service;

import com.samin.auth.exception.BusException;
import com.samin.auth.exception.ExceptionEnums;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

@Slf4j
@Service
public class PermissionService {


    public boolean access() {
        log.info("access!");

        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (Objects.isNull(requestAttributes)) {
            throw new BusException(ExceptionEnums.SYSTEM_ERROR);
        }

        HttpServletRequest request = requestAttributes.getRequest();

        String header = request.getHeader("Authorization");
        if (Objects.isNull(header) || "".equals(header)) {
            throw new BadCredentialsException("请添加 Token 请求头");
        }

        // 授权逻辑

        // 不做授权
        return true;
    }
}
