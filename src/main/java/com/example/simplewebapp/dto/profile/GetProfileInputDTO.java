package com.example.simplewebapp.dto.profile;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import jakarta.validation.constraints.NotBlank;
import org.springframework.web.bind.annotation.BindParam;

public record GetProfileInputDTO (
        @BindParam("profile_uid")
        @Parameter(in = ParameterIn.PATH, name = "profile_uid", required = true)
        @NotBlank
        String profileUid
) {}

