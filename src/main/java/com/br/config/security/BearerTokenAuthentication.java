package com.br.config.security;

import org.springframework.security.authentication.AbstractAuthenticationToken;

import java.util.Collections;

public class BearerTokenAuthentication extends AbstractAuthenticationToken {

    private final String token;

    public BearerTokenAuthentication(String token) {
        super(Collections.emptyList());
        this.token = token;
        setAuthenticated(true);
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return this.token;
    }
}