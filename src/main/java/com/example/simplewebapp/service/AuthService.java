package com.example.simplewebapp.service;

import com.example.simplewebapp.config.redis.TokenStore;
import com.example.simplewebapp.config.security.JwtTokenProvider;
import com.example.simplewebapp.dto.auth.LoginRequest;
import com.example.simplewebapp.dto.auth.LoginResponse;
import com.example.simplewebapp.dto.auth.SignupRequest;
import com.example.simplewebapp.dto.auth.SignupResponse;
import com.example.simplewebapp.model.UserEntity;
import com.example.simplewebapp.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final TokenStore tokenStore;

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtTokenProvider jwtTokenProvider, TokenStore tokenStore) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenProvider = jwtTokenProvider;
        this.tokenStore = tokenStore;
    }

    public LoginResponse login(LoginRequest request) {
        UserEntity user = userRepository
                .findActiveUserByEmail(request.email())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!passwordEncoder.matches(request.password(), user.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }

        String authToken = jwtTokenProvider.generateAuthToken(user.getUserUid());
        String refreshToken = jwtTokenProvider.generateRefreshToken(user.getUserUid());

        tokenStore.storeAuthToken(authToken, user.getUserUid(), jwtTokenProvider.getAuthExpiration());
        tokenStore.storeRefreshToken(refreshToken, user.getUserUid(), jwtTokenProvider.getRefreshExpiration());

        return new LoginResponse(authToken, refreshToken);
    }

    public SignupResponse signUp(SignupRequest request) {
        if (userRepository.findActiveUserByEmail(request.email()).isPresent()) {
            throw new RuntimeException("Email already in use");
        }

        UserEntity user = new UserEntity();
        user.setFullName(request.fullName());
        user.setAge(request.age());
        user.setEmail(request.email());
        user.setPassword(passwordEncoder.encode(request.password()));
        user.setJoinedAt(new Date());
        user.setIsDeleted(false);

        userRepository.save(user);

        String authToken = jwtTokenProvider.generateAuthToken(user.getUserUid());
        String refreshToken = jwtTokenProvider.generateRefreshToken(user.getUserUid());

        tokenStore.storeAuthToken(authToken, user.getUserUid(), jwtTokenProvider.getAuthExpiration());
        tokenStore.storeRefreshToken(refreshToken, user.getUserUid(), jwtTokenProvider.getRefreshExpiration());

        return new SignupResponse(authToken, refreshToken);
    }

    public void logout(String authToken) {
        tokenStore.removeAuthToken(authToken);
    }

    public LoginResponse refreshAccessToken(String refreshToken) {
        if (!jwtTokenProvider.validateToken(refreshToken)) {
            throw new RuntimeException("Invalid refresh token");
        }

        String userUid = jwtTokenProvider.getUserUidFromToken(refreshToken);
        String storedUid = tokenStore.getRefreshTokenUserUid(refreshToken);
        if (storedUid == null || !storedUid.equals(userUid)) {
            throw new RuntimeException("Refresh token has been revoked or does not exist");
        }

        tokenStore.removeRefreshToken(refreshToken);

        String newAuthToken = jwtTokenProvider.generateAuthToken(userUid);
        String newRefreshToken = jwtTokenProvider.generateRefreshToken(userUid);

        tokenStore.storeAuthToken(newAuthToken, userUid, jwtTokenProvider.getAuthExpiration());
        tokenStore.storeRefreshToken(newRefreshToken, userUid, jwtTokenProvider.getRefreshExpiration());

        return new LoginResponse(newAuthToken, newRefreshToken);
    }
}
