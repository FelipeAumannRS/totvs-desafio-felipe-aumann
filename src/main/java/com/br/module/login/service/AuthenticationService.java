package com.br.module.login.service;

import com.br.config.security.TokenService;
import com.br.module.login.domain.dto.LoginRequestDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final TokenService tokenService;
    private final AuthenticationManager authenticationManager;

    public String authenticate(LoginRequestDTO loginRequest) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword())
            );

            if (!authentication.isAuthenticated())
                throw new BadCredentialsException("Invalid credentials");

            return tokenService.generateToken(authentication.getName());

        } catch (Exception ex) {
            log.error("Unable to authenticate login request", ex);
            return Strings.EMPTY;
        }
     }

    public String refreshAccessToken(String refreshToken) {
        if (!tokenService.isValidRefreshToken(refreshToken))
            throw new BadCredentialsException("Invalid refresh token");

        String username = tokenService.getUsernameFromRefreshToken(refreshToken);
        return tokenService.generateToken(username);
    }
}