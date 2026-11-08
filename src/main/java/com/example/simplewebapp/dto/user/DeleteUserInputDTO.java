package com.example.simplewebapp.dto.user;

import jakarta.validation.constraints.NotBlank;

public record DeleteUserInputDTO (
        @NotBlank
        String userUid
) {}