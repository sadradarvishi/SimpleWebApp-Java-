package com.example.simplewebapp.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import java.util.Date;


public record CreateUserInputDTO (
    @NotBlank
    String fullName,

    @NotBlank
    Integer age,

    Date joinedAt,

    Boolean isDeleted,

    @NotBlank
    @Email
    String email,

    @NotBlank
    String password
) {
    public CreateUserInputDTO {
        if (joinedAt == null) {
            joinedAt = new Date();
        }
        if (isDeleted == null) {
            isDeleted = false;
        }
    }
}
