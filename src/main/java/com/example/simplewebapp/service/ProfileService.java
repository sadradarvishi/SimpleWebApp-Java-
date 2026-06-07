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

    private final ProfileRepository profileRepository;
    private final UserRepository userRepository;

    public ProfileService(ProfileRepository profileRepository, UserRepository userRepository) {
        this.userRepository = userRepository;
        this.profileRepository = profileRepository;
    }

    public ProfileEntity getProfile(String profile_uid) {
        return profileRepository.getById(profile_uid);
    }

    public List<ProfileEntity> searchProfiles() {
        return profileRepository.searchProfileWithUser();
    }

    public ProfileEntity createProfile(String user_uid, Map<String, Object> input) {
        if (!input.containsKey("display_name")) {
            throw new IllegalArgumentException("display_name is required");
        }

        if (!input.containsKey("location")) {
            throw new IllegalArgumentException("location is required");
        }

        UserEntity user = userRepository.findById(user_uid)
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

        return profileRepository.save(profile);
    }
}
