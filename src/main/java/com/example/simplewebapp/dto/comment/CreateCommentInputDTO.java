package com.example.simplewebapp.dto.comment;

import jakarta.validation.constraints.NotBlank;

public record CreateCommentInputDTO (
        @NotBlank
        String postUid,

        @NotBlank
        String userUid,

        @NotBlank
        String content
) {}

