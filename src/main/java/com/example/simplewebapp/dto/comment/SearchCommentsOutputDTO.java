package com.example.simplewebapp.dto.comment;

import java.util.Date;

public record SearchCommentsOutputDTO (
        String commentUid,
        String content,
        String postUid,
        String userUid,
        Date createdAt,
        Date updatedAt,
        Boolean isDeleted
) {}
