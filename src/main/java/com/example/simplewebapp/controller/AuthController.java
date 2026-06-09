package com.example.simplewebapp.controller;

import com.example.simplewebapp.dto.auth.LoginRequest;
import com.example.simplewebapp.dto.auth.LoginResponse;
import com.example.simplewebapp.dto.auth.RefreshTokenRequest;
import com.example.simplewebapp.dto.auth.SignupRequest;
import com.example.simplewebapp.dto.auth.SignupResponse;
import com.example.simplewebapp.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login/")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest request) {
        LoginResponse tokens = authService.login(request);
        return ResponseEntity.ok(tokens);
    }

    @PostMapping("/signup/")
    public ResponseEntity<SignupResponse> signUp(@Valid @RequestBody SignupRequest request) {
        SignupResponse tokens = authService.signUp(request);
        return ResponseEntity.ok(tokens);
    }

    @PostMapping("/logout/")
    public ResponseEntity<Void> logout(@RequestHeader("Authorization") String authHeader) {
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);
            authService.logout(token);
        }
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/refresh/")
    public ResponseEntity<LoginResponse> refresh(@Valid @RequestBody RefreshTokenRequest request) {
        LoginResponse tokens = authService.refreshAccessToken(request.refreshToken());
        return ResponseEntity.ok(tokens);
    }
}
