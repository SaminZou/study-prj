package com.samin.auth.authentication;

import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsChecker;
import org.springframework.stereotype.Component;

@Component
public class CustomUserDetailsChecker implements UserDetailsChecker {

    @Override
    public void check(UserDetails userDetails) {
        if (!userDetails.isAccountNonExpired()) {
            // 抛出过期异常
            throw new AccountExpiredException("Account Expired");
        }

        if (!userDetails.isAccountNonLocked()) {
            // 抛出锁定异常
            throw new LockedException("Account Locked");
        }

        if (!userDetails.isEnabled()) {
            // 抛出禁用异常
            throw new DisabledException("Account Disabled");
        }
    }
}
