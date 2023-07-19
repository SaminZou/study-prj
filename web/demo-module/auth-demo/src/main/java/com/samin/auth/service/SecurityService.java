package com.samin.auth.service;

import com.samin.auth.authentication.CustomUserDetails;
import com.samin.auth.common.SystemConstant;
import com.samin.auth.exception.ExceptionEnums;
import com.samin.auth.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

/**
 * 认证相关服务类
 *
 * @author samin
 * @date 2023-07-19
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class SecurityService {

    private final JwtUtil jwtUtil;
    @Value("${jwt.tokenHeader}")
    private String tokenHeader;
    @Value("${jwt.tokenHead}")
    private String tokenHead;

    public CustomUserDetails getCurrentUser() {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (Objects.isNull(requestAttributes)) {
            return null;
        }

        HttpServletRequest request = requestAttributes.getRequest();

        String header = request.getHeader(tokenHeader);
        if (Objects.isNull(header) || "".equals(header)) {
            throw new BadCredentialsException("请添加 Token 请求头");
        }
        String authToken = header.substring(this.tokenHead.length());

        // TODO 修改为从 redis 中获取
        CustomUserDetails userDetails = LoginService.tokenStore.get(Strings.concat(SystemConstant.LOGIN_TOKEN_CACHE_PREFIX, jwtUtil.getIdFromToken(authToken)));

        if (Objects.isNull(userDetails)) {
            ExceptionEnums.throwException(ExceptionEnums.USER_NOT_EXIST_ERROR);
        }

        return userDetails;
    }
}
