package com.example.simplewebapp.dto.profile;

public record GetProfileOutputDTO (
        String profileUid,
        String userUid,
        String displayName,
        String bio,
        String location,
        String website
) {}
