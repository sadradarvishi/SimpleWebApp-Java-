package com.example.simplewebapp.dto.user;

import jakarta.validation.constraints.NotBlank;

import java.util.Date;


public record GetUserOutputDTO (
        @NotBlank
        String userUid,

        @NotBlank
        String fullName,

        @NotBlank
        Integer age,

        @NotBlank
        Date joinedAt,

        @NotBlank
        Boolean isDeleted,

        @NotBlank
        String email
) {}
