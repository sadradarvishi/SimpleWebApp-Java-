package com.example.simplewebapp.dto.profile;


public record CreateProfileOutputDTO (
        String profileUid,
        String displayName,
        String bio,
        String location,
        String website,
        Boolean isDeleted
) {}
