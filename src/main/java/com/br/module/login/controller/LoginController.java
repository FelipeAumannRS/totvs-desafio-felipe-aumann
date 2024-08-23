package com.br.module.login.controller;

import com.br.config.security.TokenService;
import com.br.module.login.domain.dto.LoginRequestDTO;
import com.br.module.login.domain.dto.RefreshTokenRequestDTO;
import com.br.module.login.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class LoginController {

    private final AuthenticationService authenticationService;
    private final TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDTO loginRequest) {
        String token = authenticationService.authenticate(loginRequest);
        String refreshToken = tokenService.generateRefreshToken(loginRequest.getUsername());
        return ResponseEntity.ok(Map.of("token", token, "refreshToken", refreshToken));
    }

    @PostMapping("/refresh")
    public ResponseEntity<?> refresh(@RequestBody RefreshTokenRequestDTO refreshTokenRequest) {
        String newAccessToken = authenticationService.refreshAccessToken(refreshTokenRequest.getRefreshToken());
        return ResponseEntity.ok(Map.of("token", newAccessToken));
    }
}