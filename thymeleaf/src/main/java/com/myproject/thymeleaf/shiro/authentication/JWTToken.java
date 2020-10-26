package com.myproject.thymeleaf.shiro.authentication;

import lombok.Data;
import org.apache.shiro.authc.AuthenticationToken;

@Data
public class JWTToken implements AuthenticationToken {
    private static final long serialVersionUID = 1282057025599826155L;

    private String token;

    private String expiration;

    public JWTToken(String token) {
        this.token = token;
    }

    public JWTToken(String token, String expiration) {
        this.token = token;
        this.expiration = expiration;
    }

    @Override
    public Object getPrincipal() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return token;
    }
}
