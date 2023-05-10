package com.samin.auth.service;


import com.samin.auth.authentication.CustomUserDetails;
import com.samin.auth.db.DBService;
import java.util.Objects;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * 自定义用户信息加载服务类
 *
 * @author samin
 * @date 2022-08-08
 */
@Slf4j
@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        CustomUserDetails customUserDetails = DBService.USER_DETAILS_MAP.get(username);

        if (Objects.isNull(customUserDetails)) {
            throw new UsernameNotFoundException("用户不存在");
        }

        return customUserDetails;
    }
}
