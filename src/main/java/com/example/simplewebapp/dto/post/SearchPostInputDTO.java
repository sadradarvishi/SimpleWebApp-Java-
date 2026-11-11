package com.example.simplewebapp.dto.post;

import java.util.Date;

public record SearchPostInputDTO (
        String userUid,
        Date createdAtFrom,
        Date createdAtTo,
        String title
) {}
