package com.samin.auth.service;

import com.samin.auth.exception.BusException;
import com.samin.auth.exception.ExceptionEnums;
import com.samin.auth.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

/**
 * 权限服务类
 *
 * @author samin
 * @date 2023-07-12
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class PermissionService {

    private final JwtUtil jwtUtil;
    private final UserDetailsService userDetailsService;
    @Value("${jwt.tokenHeader}")
    private String tokenHeader;
    @Value("${jwt.tokenHead}")
    private String tokenHead;

    public boolean access() {
        log.info("access!");

        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (Objects.isNull(requestAttributes)) {
            throw new BusException(ExceptionEnums.SYSTEM_ERROR);
        }

        HttpServletRequest request = requestAttributes.getRequest();

        String header = request.getHeader(tokenHeader);
        if (Objects.isNull(header) || "".equals(header)) {
            throw new BadCredentialsException("请添加 Token 请求头");
        }
        String authToken = header.substring(this.tokenHead.length());
        String username = jwtUtil.getUserNameFromToken(authToken);
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);

        if (jwtUtil.validateToken(authToken, userDetails)) {
            return true;
        }

        // 授权逻辑

        // 不做授权

        return true;
    }
}
