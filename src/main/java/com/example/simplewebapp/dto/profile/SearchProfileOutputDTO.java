package com.example.simplewebapp.dto.profile;

import com.example.simplewebapp.dto.user.GetUserOutputDTO;

import java.util.Date;


public record SearchProfileOutputDTO (
        String profileUid,
        String userUid,
        GetUserOutputDTO user,
        String displayName,
        String bio,
        String location,
        String website,
        Boolean isDeleted
) {
    public SearchProfileOutputDTO(
            String profileUid,
            String userUid,
            String fullName,
            Integer age,
            Date joinedAt,
            Boolean userIsDeleted,
            String email,
            String displayName,
            String bio,
            String location,
            String website,
            Boolean isDeleted
    ) {
        this(profileUid, userUid,
                new GetUserOutputDTO(userUid, fullName, age, joinedAt, userIsDeleted, email),
                displayName, bio, location, website, isDeleted);
    }
}

