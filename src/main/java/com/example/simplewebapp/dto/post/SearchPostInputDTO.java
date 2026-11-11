package com.example.simplewebapp.dto.post;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public record SearchPostInputDTO (
        String userUid,

        @DateTimeFormat(pattern = "yyyy-MM-dd")
        Date createdAtFrom,

        @DateTimeFormat(pattern = "yyyy-MM-dd")
        Date createdAtTo,

        String title
) {}
