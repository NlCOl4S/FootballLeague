package com.futbolliga.controller;

import com.futbolliga.dto.LoginRequest;
import com.futbolliga.dto.RegisterRequest;
import com.futbolliga.dto.TokenResponse;
import com.futbolliga.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor

public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<TokenResponse> register(@RequestBody RegisterRequest request){
        final TokenResponse token = authService.register(request);
        return ResponseEntity.ok(token);
    }

    @PostMapping("/login")
    public ResponseEntity<TokenResponse> login(@RequestBody LoginRequest request){
        final TokenResponse token = authService.login(request);
        return ResponseEntity.ok(token);
    }

    @PostMapping("/refresh-token")
    public ResponseEntity<TokenResponse> refreshToken(@RequestHeader(HttpHeaders.AUTHORIZATION) final String authHeader){
        final TokenResponse token = authService.refreshToken(authHeader);
        return ResponseEntity.ok(token);
    }
}
