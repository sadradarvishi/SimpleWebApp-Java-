package com.example.simplewebapp.dto.auth;

import jakarta.validation.constraints.NotBlank;

public record LoginResponse(

        @NotBlank
        String authToken,

        @NotBlank
        String refreshToken
) {}