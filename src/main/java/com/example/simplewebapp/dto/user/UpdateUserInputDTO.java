package com.example.simplewebapp.dto.user;

import jakarta.validation.constraints.NotBlank;

public record UpdateUserInputDTO (
        @NotBlank
        String userUid,

        String fullName,
        Integer age,
        String email,
        String password
) {}
