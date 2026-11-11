package com.example.simplewebapp.dto.post;

import jakarta.validation.constraints.NotBlank;


public record CreatePostInputDTO (
        @NotBlank
        String userUid,

        @NotBlank
        String content,

        @NotBlank
        String title,

        Boolean isDeleted
) {
    public CreatePostInputDTO {
        if (isDeleted == null) {
            isDeleted = false;
        }
    }
}

