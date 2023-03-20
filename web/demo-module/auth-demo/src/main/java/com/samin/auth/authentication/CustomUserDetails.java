package com.samin.auth.authentication;

import java.util.Collection;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Data
public class CustomUserDetails implements UserDetails {

    private String username;
    private String password;

    public static CustomUserDetails getInstance(String username, String password) {
        CustomUserDetails ins = new CustomUserDetails();

        ins.setUsername(username);
        ins.setPassword(new BCryptPasswordEncoder().encode(password));

        return ins;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
