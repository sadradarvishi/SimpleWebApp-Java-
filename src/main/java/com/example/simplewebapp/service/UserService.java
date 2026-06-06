package com.example.simplewebapp.service;

import com.example.simplewebapp.model.UserEntity;
import com.example.simplewebapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public UserEntity createUser(UserEntity input) {
        return userRepository.save(input);
    }

    public UserEntity getUserById(String user_uid) {
        return userRepository.findById(user_uid)
                .orElseThrow(() -> new RuntimeException("No user found"));
    }

    public List<UserEntity> searchUsers() {
        return userRepository.searchUsers();
    }

    public UserEntity updateUser(String user_uid, Map<String, Object> input) {
        UserEntity existingUser = userRepository.findById(user_uid)
                .orElseThrow(() -> new RuntimeException("User not found with uid: " + user_uid));

        if (input.containsKey("full_name")) {
            existingUser.setFullName((String) input.get("full_name"));
        }

        if (input.containsKey("age")) {
            existingUser.setAge((Integer) input.get("age"));
        }

        if (input.containsKey("joined_at")) {
            Object dateObj = input.get("joined_at");
            if (dateObj instanceof Date) {
                existingUser.setJoinedAt((Date) dateObj);
            }
        }

        return userRepository.save(existingUser);
    }

    public void deleteUser(String user_uid) {
        UserEntity user = userRepository.findById(user_uid)
                .orElseThrow(() -> new RuntimeException("No user found"));

        user.setIsDeleted(true);
        userRepository.save(user);
    }
}
