package com.example.simplewebapp.service;

import com.example.simplewebapp.model.ProfileEntity;
import com.example.simplewebapp.model.UserEntity;
import com.example.simplewebapp.repository.ProfileRepository;
import com.example.simplewebapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ProfileService {

    @Autowired
    ProfileRepository profile_repository;

    @Autowired
    UserRepository user_repository;

    public ProfileEntity getProfile(String profile_uid) {
        return profile_repository.getById(profile_uid);
    }

    public List<ProfileEntity> searchProfiles() {
        return profile_repository.findAll();
    }

    public ProfileEntity createProfile(String user_uid, Map<String, Object> input) {
        if (!input.containsKey("display_name")) {
            throw new IllegalArgumentException("display_name is required");
        }

        if (!input.containsKey("location")) {
            throw new IllegalArgumentException("location is required");
        }

        UserEntity user = user_repository.findById(user_uid)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        ProfileEntity profile = new ProfileEntity();

        profile.setUser(user);
        profile.setDisplayName((String) input.get("display_name"));
        profile.setLocation((String) input.get("location"));

        if (input.containsKey("bio")) {
            profile.setBio((String) input.get("bio"));
        }

        if (input.containsKey("website")) {
            profile.setWebsite((String) input.get("website"));
        }

        return profile_repository.save(profile);
    }
}
