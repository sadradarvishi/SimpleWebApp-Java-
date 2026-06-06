package com.example.simplewebapp.controller;

import com.example.simplewebapp.model.ProfileEntity;
import com.example.simplewebapp.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/v1/profiles")
public class ProfileController {

    @Autowired
    ProfileService profile_service;

    @GetMapping("/{profile_uid}/")
    public ResponseEntity<ProfileEntity> getProfile(@PathVariable String profile_uid) {
        return ResponseEntity.ok(profile_service.getProfile(profile_uid));
    }

    @GetMapping("/")
    public ResponseEntity<List<ProfileEntity>> searchProfiles() {
        return ResponseEntity.ok(profile_service.searchProfiles());
    }

    @PostMapping("/")
    public ResponseEntity<ProfileEntity> createProfile(
        @RequestHeader("user_uid") String user_uid,
        @RequestBody Map<String, Object> input
    ) {
        return ResponseEntity.ok(profile_service.createProfile(user_uid, input));
    }
}
