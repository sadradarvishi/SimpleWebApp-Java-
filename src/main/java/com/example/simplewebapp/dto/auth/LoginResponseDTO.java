package com.example.simplewebapp.dto.auth;

import jakarta.validation.constraints.NotBlank;

public record LoginResponseDTO(

        @NotBlank
        String accessToken,

        @NotBlank
        String refreshToken
) {}