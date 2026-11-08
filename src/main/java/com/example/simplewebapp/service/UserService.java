package com.example.simplewebapp.service;

import com.example.simplewebapp.model.UserEntity;
import com.example.simplewebapp.repository.UserRepository;
import com.example.simplewebapp.dto.user.CreateUserInputDTO;
import com.example.simplewebapp.dto.user.CreateUserOutputDTO;
import com.example.simplewebapp.dto.user.GetUserOutputDTO;
import com.example.simplewebapp.dto.user.SearchUserInputDTO;
import com.example.simplewebapp.dto.user.SearchUserOutputDTO;
import com.example.simplewebapp.dto.user.UpdateUserInputDTO;
import com.example.simplewebapp.dto.user.UpdateUserOutputDTO;
import com.example.simplewebapp.dto.user.DeleteUserInputDTO;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder
        ) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public CreateUserOutputDTO createUser(CreateUserInputDTO inputDto) {
        UserEntity user = new UserEntity();
        user.setFullName(inputDto.fullName());
        user.setAge(inputDto.age());
        user.setJoinedAt(new Date());
        user.setIsDeleted(inputDto.isDeleted());
        user.setEmail(inputDto.email());
        user.setPassword(passwordEncoder.encode(inputDto.password()));

        UserEntity userEntity = userRepository.save(user);

        return new CreateUserOutputDTO(
                userEntity.getUserUid(),
                userEntity.getFullName(),
                userEntity.getAge(),
                userEntity.getJoinedAt(),
                userEntity.getIsDeleted(),
                userEntity.getEmail()
        );
    }

    public GetUserOutputDTO getUserById(String userUid) {
        UserEntity user =  userRepository.getUserById(userUid)
                .orElseThrow(() -> new RuntimeException("No user found"));

        return new GetUserOutputDTO(
                user.getUserUid(),
                user.getFullName(),
                user.getAge(),
                user.getJoinedAt(),
                user.getIsDeleted(),
                user.getEmail()
        );
    }

    public List<SearchUserOutputDTO> searchUsers(SearchUserInputDTO inputDTO) {
        return userRepository.searchUsers(
                inputDTO.fullName(),
                inputDTO.email(),
                inputDTO.joinedDateFrom(),
                inputDTO.joinedDateTo()
        );
    }

    public UpdateUserOutputDTO updateUser(UpdateUserInputDTO inputDTO) {
        UserEntity existingUser = userRepository.findById(inputDTO.userUid())
                .orElseThrow(() -> new RuntimeException("User not found with uid: " + inputDTO.userUid()));

        if (inputDTO.fullName() != null) {
            existingUser.setFullName(inputDTO.fullName());
        }

        if (inputDTO.age() != null) {
            existingUser.setAge(inputDTO.age());
        }

        if (inputDTO.email() != null) {
            existingUser.setEmail(inputDTO.email());
        }

        if (inputDTO.password() != null) {
            String encodedPassword = passwordEncoder.encode(inputDTO.password());
            existingUser.setPassword(encodedPassword);
        }

        userRepository.save(existingUser);

        return new UpdateUserOutputDTO(
                existingUser.getUserUid(),
                existingUser.getFullName(),
                existingUser.getAge(),
                existingUser.getJoinedAt(),
                existingUser.getIsDeleted(),
                existingUser.getEmail()
        );
    }

    public void deleteUser(DeleteUserInputDTO inputDTO) {
        UserEntity user = userRepository.findById(inputDTO.userUid())
                .orElseThrow(() -> new RuntimeException("No user found"));

        user.setIsDeleted(true);
        userRepository.save(user);
    }
}
