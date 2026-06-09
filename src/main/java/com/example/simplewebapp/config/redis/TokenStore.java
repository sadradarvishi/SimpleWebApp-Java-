package com.example.simplewebapp.config.redis;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class TokenStore {

    private static final String AUTH_PREFIX = "auth:";
    private static final String REFRESH_PREFIX = "refresh:";

    private final StringRedisTemplate redisTemplate;

    public TokenStore(StringRedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public void storeAuthToken(String token, String userUid, long expirationMs) {
        redisTemplate.opsForValue().set(
                AUTH_PREFIX + token,
                userUid,
                expirationMs,
                TimeUnit.MILLISECONDS
        );
    }

    public void storeRefreshToken(String token, String userUid, long expirationMs) {
        redisTemplate.opsForValue().set(
                REFRESH_PREFIX + token,
                userUid,
                expirationMs,
                TimeUnit.MILLISECONDS
        );
    }

    public String getAuthTokenUserUid(String token) {
        return redisTemplate.opsForValue().get(AUTH_PREFIX + token);
    }

    public String getRefreshTokenUserUid(String token) {
        return redisTemplate.opsForValue().get(REFRESH_PREFIX + token);
    }

    public void removeAuthToken(String token) {
        redisTemplate.delete(AUTH_PREFIX + token);
    }

    public void removeRefreshToken(String token) {
        redisTemplate.delete(REFRESH_PREFIX + token);
    }
}
