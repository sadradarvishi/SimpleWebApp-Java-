package com.example.simplewebapp.dto.post;

import java.util.Date;

public record GetPostOutputDTO (
        String postUid,
        String userUid,
        Date createdAt,
        Date updatedAt,
        String content,
        String title,
        boolean isDeleted
) {}

