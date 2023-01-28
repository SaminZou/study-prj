package com.samin.auth.authentication;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.authentication.AbstractAuthenticationToken;

@Getter
@Setter
public class CustomAuthenticationToken extends AbstractAuthenticationToken {

    private String username;
    private String password;

    public CustomAuthenticationToken(String username, String password) {
        super(null);
        this.username = username;
        this.password = password;
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return null;
    }
}
