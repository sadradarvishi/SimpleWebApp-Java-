package com.example.simplewebapp.dto.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public record SearchUserOutputDTO (
        @NotNull
        String userUid,

        @NotBlank
        String fullName,

        @NotNull
        Integer age,

        @NotNull
        Date joinedAt,

        @NotNull
        Boolean isDeleted,

        @NotBlank
        String email
)
{}
