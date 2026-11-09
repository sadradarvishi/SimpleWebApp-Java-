package com.example.simplewebapp.dto.profile;

import jakarta.validation.constraints.NotBlank;

public record CreateProfileInputDTO (
        @NotBlank
        String userUid,

        @NotBlank
        String displayName,

        String bio,

        @NotBlank
        String location,

        String website
) {}

