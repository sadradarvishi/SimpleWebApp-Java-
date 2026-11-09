package com.example.simplewebapp.controller;

import com.example.simplewebapp.service.ProfileService;
import com.example.simplewebapp.dto.profile.GetProfileInputDTO;
import com.example.simplewebapp.dto.profile.GetProfileOutputDTO;
import com.example.simplewebapp.dto.profile.SearchProfileOutputDTO;
import com.example.simplewebapp.dto.profile.CreateProfileInputDTO;
import com.example.simplewebapp.dto.profile.CreateProfileOutputDTO;
import jakarta.validation.Valid;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/profiles")
public class ProfileController {

    private final ProfileService profile_service;

    public ProfileController(ProfileService profile_service) {
        this.profile_service = profile_service;
    }

    @GetMapping("/{profile_uid}/")
    public ResponseEntity<GetProfileOutputDTO> getProfile(@Valid @ParameterObject GetProfileInputDTO inputDTO) {
        return ResponseEntity.ok(profile_service.getProfile(inputDTO));
    }

    @GetMapping("/")
    public ResponseEntity<List<SearchProfileOutputDTO>> searchProfiles() {
        return ResponseEntity.ok(profile_service.searchProfiles());
    }

    @PostMapping("/")
    public ResponseEntity<CreateProfileOutputDTO> createProfile(@ParameterObject CreateProfileInputDTO inputDTO) {
        return ResponseEntity.ok(profile_service.createProfile(inputDTO));
    }
}
