package com.example.simplewebapp.dto.auth;

import jakarta.validation.constraints.NotBlank;

public record SignupResponse(
        @NotBlank
        String authToken,

        @NotBlank
        String refreshToken
) {}
