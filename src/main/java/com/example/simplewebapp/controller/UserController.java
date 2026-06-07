package com.example.simplewebapp.controller;

import com.example.simplewebapp.model.UserEntity;
import com.example.simplewebapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/v1/users")
public class UserController {

    private final UserService user_service;

    public UserController(UserService user_service) {
        this.user_service = user_service;
    }

    @PostMapping("/")
    public ResponseEntity<UserEntity> createUser(@RequestBody UserEntity input) {
        return ResponseEntity.ok(user_service.createUser(input));
    }

    @GetMapping("/{user_uid}/")
    public ResponseEntity<UserEntity> getUser(@PathVariable String user_uid) {
        return ResponseEntity.ok(user_service.getUserById(user_uid));
    }

    @GetMapping("/")
    public ResponseEntity<List<UserEntity>> searchUsers() {
        return ResponseEntity.ok(user_service.searchUsers());
    }

    @PatchMapping("/")
    public ResponseEntity<UserEntity> updateUser(
            @RequestHeader("user_uid") String user_uid,
            @RequestBody Map<String, Object> input
    ) {
        UserEntity updatedUser = user_service.updateUser(user_uid, input);
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/{user_uid}/")
    public ResponseEntity<Void> deleteUser(@PathVariable String user_uid) {
        user_service.deleteUser(user_uid);
        return ResponseEntity.noContent().build();
    }
}
