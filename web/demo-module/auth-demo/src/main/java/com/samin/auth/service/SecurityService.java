package com.samin.auth.service;

import cn.hutool.core.util.StrUtil;
import com.samin.auth.authentication.CustomUserDetails;
import com.samin.auth.exception.ExceptionEnums;
import com.samin.auth.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RBucket;
import org.redisson.api.RedissonClient;
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
    private final RedissonClient redissonClient;
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
        if (StrUtil.isBlank(header)) {
            throw new BadCredentialsException("请添加 Token 请求头");
        }
        String authToken = header.substring(this.tokenHead.length());

        String idFromToken = jwtUtil.getIdFromToken(authToken);
        if (StrUtil.isNotBlank(idFromToken)) {
            RBucket<CustomUserDetails> userDetailsRedisBucket = redissonClient.getBucket("token:" + Integer.parseInt(idFromToken));
            CustomUserDetails userDetails = userDetailsRedisBucket.get();

            if (Objects.isNull(userDetails)) {
                ExceptionEnums.throwException(ExceptionEnums.USER_NOT_EXIST_ERROR);
            }

            return userDetails;
        } else {
            ExceptionEnums.throwException(ExceptionEnums.USER_NOT_EXIST_ERROR);
        }

        return null;
    }
}
