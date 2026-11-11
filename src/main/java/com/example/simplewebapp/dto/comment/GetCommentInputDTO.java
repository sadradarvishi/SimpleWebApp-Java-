package com.example.simplewebapp.dto.comment;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import jakarta.validation.constraints.NotBlank;
import org.springframework.web.bind.annotation.BindParam;

public record GetCommentInputDTO (
        @BindParam("comment_uid")
        @Parameter(in = ParameterIn.PATH, name = "comment_uid", required = true)
        @NotBlank
        String commentUid
) {}

