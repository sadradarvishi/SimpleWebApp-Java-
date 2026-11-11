package com.example.simplewebapp.dto.comment;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public record SearchCommentsInputDTO (
        String postUid,
        String userUid,

        @DateTimeFormat(pattern = "yyyy-MM-dd")
        Date createdAtFrom,

        @DateTimeFormat(pattern = "yyyy-MM-dd")
        Date createdAtTo,

        Boolean isDeleted
){}
