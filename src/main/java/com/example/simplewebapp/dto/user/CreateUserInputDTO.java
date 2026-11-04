package com.example.simplewebapp.dto.user;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import java.util.Date;


public record CreateUserInputDTO (
    @NotBlank
    String fullName,

    @NotBlank
    Integer age,

    @Schema(defaultValue = "false")
    Boolean isDeleted,

    @NotBlank
    @Email
    String email,

    @NotBlank
    String password
) {
    public CreateUserInputDTO {
        if (isDeleted == null) {
            isDeleted = false;
        }
    }
}
