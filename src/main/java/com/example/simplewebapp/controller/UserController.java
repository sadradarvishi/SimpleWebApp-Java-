package com.example.simplewebapp.controller;

import com.example.simplewebapp.model.UserEntity;
import com.example.simplewebapp.service.UserService;
import com.example.simplewebapp.dto.user.CreateUserInputDTO;
import com.example.simplewebapp.dto.user.CreateUserOutputDTO;
import com.example.simplewebapp.dto.user.GetUserOutputDTO;
import com.example.simplewebapp.dto.user.SearchUserInputDTO;
import com.example.simplewebapp.dto.user.SearchUserOutputDTO;
import com.example.simplewebapp.dto.user.UpdateUserInputDTO;
import com.example.simplewebapp.dto.user.UpdateUserOutputDTO;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/v1/users")
public class UserController {

    private final UserService user_service;

    public UserController(UserService user_service) {
        this.user_service = user_service;
    }

    @PostMapping("/sign-up/")
    public ResponseEntity<CreateUserOutputDTO> createUser(@RequestBody CreateUserInputDTO inputDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(user_service.createUser(inputDto));
    }

    @GetMapping("/{user_uid}/")
    public ResponseEntity<GetUserOutputDTO> getUser(@PathVariable("user_uid") String userUid) {
        return ResponseEntity.ok(user_service.getUserById(userUid));
    }

    @GetMapping("/")
    public ResponseEntity<List<SearchUserOutputDTO>> searchUsers(@ParameterObject SearchUserInputDTO inputDTO) {
        return ResponseEntity.ok(user_service.searchUsers(inputDTO));
    }

    @PatchMapping("/")
    public ResponseEntity<UpdateUserOutputDTO> updateUser(@ParameterObject UpdateUserInputDTO inputDTO) {
        return ResponseEntity.ok(user_service.updateUser(inputDTO));
    }

    @DeleteMapping("/{user_uid}/")
    public ResponseEntity<Void> deleteUser(@PathVariable String user_uid) {
        user_service.deleteUser(user_uid);
        return ResponseEntity.noContent().build();
    }
}
