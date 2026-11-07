package com.example.simplewebapp.dto.user;

import jakarta.validation.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;


public record SearchUserInputDTO (
        String fullName,

        @DateTimeFormat(pattern = "yyyy-MM-dd")
        Date joinedDateFrom,

        @DateTimeFormat(pattern = "yyyy-MM-dd")
        Date joinedDateTo,

        String email
) {}
