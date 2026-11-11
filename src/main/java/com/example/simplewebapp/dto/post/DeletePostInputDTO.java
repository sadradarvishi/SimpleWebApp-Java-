package com.example.simplewebapp.dto.post;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import jakarta.validation.constraints.NotBlank;
import org.springframework.web.bind.annotation.BindParam;

public record DeletePostInputDTO (
        @BindParam("post_uid")
        @Parameter(in = ParameterIn.PATH, name = "post_uid", required = true)
        @NotBlank
        String postUid
) {}

