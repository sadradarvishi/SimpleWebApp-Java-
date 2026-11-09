package com.example.simplewebapp.service;

import com.example.simplewebapp.model.ProfileEntity;
import com.example.simplewebapp.model.UserEntity;
import com.example.simplewebapp.repository.ProfileRepository;
import com.example.simplewebapp.repository.UserRepository;
import com.example.simplewebapp.dto.profile.GetProfileInputDTO;
import com.example.simplewebapp.dto.profile.GetProfileOutputDTO;
import com.example.simplewebapp.dto.profile.SearchProfileOutputDTO;
import com.example.simplewebapp.dto.profile.CreateProfileInputDTO;
import com.example.simplewebapp.dto.profile.CreateProfileOutputDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfileService {

    private final ProfileRepository profileRepository;
    private final UserRepository userRepository;

    public ProfileService(ProfileRepository profileRepository, UserRepository userRepository) {
        this.userRepository = userRepository;
        this.profileRepository = profileRepository;
    }

    public GetProfileOutputDTO getProfile(GetProfileInputDTO inputDTO) {
        ProfileEntity profile =  profileRepository.getProfileById(inputDTO.profileUid())
                .orElseThrow(() -> new RuntimeException("No profile found"));

        return new GetProfileOutputDTO(
                profile.getProfileUid(),
                profile.getUser().getUserUid(),
                profile.getDisplayName(),
                profile.getBio(),
                profile.getLocation(),
                profile.getWebsite()
        );
    }

    public List<SearchProfileOutputDTO> searchProfiles() {
        return profileRepository.searchProfileWithUser();
    }

    public CreateProfileOutputDTO createProfile(CreateProfileInputDTO inputDTO) {
        UserEntity user = userRepository.findById(inputDTO.userUid())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        if (profileRepository.findProfileByUserId(inputDTO.userUid()).isPresent()) {
            throw new IllegalArgumentException("This user already has a profile");
        }

        ProfileEntity profile = new ProfileEntity();
        profile.setUserUid(inputDTO.userUid());
        profile.setDisplayName(inputDTO.displayName());
        profile.setBio(inputDTO.bio());
        profile.setLocation(inputDTO.location());
        profile.setWebsite(inputDTO.website());
        profile.setDeleted(false);

        profileRepository.save(profile);

        return new CreateProfileOutputDTO(
                profile.getProfileUid(),
                profile.getDisplayName(),
                profile.getBio(),
                profile.getLocation(),
                profile.getWebsite(),
                profile.getDeleted()
        );
    }
}
