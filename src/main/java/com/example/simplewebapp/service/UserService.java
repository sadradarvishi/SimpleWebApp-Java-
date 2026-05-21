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
    UserRepository user_repository;

    public UserEntity createUser(UserEntity input) {
        return user_repository.save(input);
    }

    public UserEntity getUserById(String user_uid) {
        return user_repository.getById(user_uid);
    }

    public List<UserEntity> searchUsers() {
        return user_repository.findAll();
    }

    public UserEntity updateUser(String user_uid, Map<String, Object> input) {
        UserEntity existingUser = user_repository.findById(user_uid)
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

        return user_repository.save(existingUser);
    }

    public void deleteUser(String user_uid) {
        UserEntity user = user_repository.getById(user_uid);
        user.setDeleted(true);
        user_repository.save(user);
    }
}
