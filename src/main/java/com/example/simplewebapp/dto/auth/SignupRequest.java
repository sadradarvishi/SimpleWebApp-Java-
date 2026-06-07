package com.example.simplewebapp.dto.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record SignupRequest(

        @NotBlank
        String fullName,

        @NotNull
        Integer age,

        @NotBlank
        @Email
        String email,

        @NotBlank
        String password
) {}
