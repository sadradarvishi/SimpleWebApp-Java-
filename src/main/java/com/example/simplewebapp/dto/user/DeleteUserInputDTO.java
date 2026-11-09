package com.example.simplewebapp.dto.user;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import jakarta.validation.constraints.NotBlank;
import org.springframework.web.bind.annotation.BindParam;

public record DeleteUserInputDTO (
        @BindParam("user_uid")
        @Parameter(in = ParameterIn.PATH, name = "user_uid", required = true)
        @NotBlank
        String userUid
) {}