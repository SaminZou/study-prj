package com.samin.auth.authentication;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsChecker;
import org.springframework.stereotype.Component;

@Component
public class CustomUserDetailsChecker implements UserDetailsChecker {

    @Override
    public void check(UserDetails toCheck) {

    }
}
