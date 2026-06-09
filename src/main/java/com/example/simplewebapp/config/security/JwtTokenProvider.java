package com.example.simplewebapp.config.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Base64;
import java.util.Date;

@Component
public class JwtTokenProvider {

    private final SecretKey key;
    private final long authExpiration;
    private final long refreshExpiration;

    public JwtTokenProvider(
            @Value("${jwt.secret}") String secret,
            @Value("${jwt.auth-expiration}") long authExpiration,
            @Value("${jwt.refresh-expiration}") long refreshExpiration
    ) {
        this.key = Keys.hmacShaKeyFor(Base64.getDecoder().decode(secret));
        this.authExpiration = authExpiration;
        this.refreshExpiration = refreshExpiration;
    }

    public String generateAuthToken(String userUid) {
        return Jwts.builder()
                .subject(userUid)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + authExpiration))
                .signWith(key)
                .compact();
    }

    public String generateRefreshToken(String userUid) {
        return Jwts.builder()
                .subject(userUid)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + refreshExpiration))
                .signWith(key)
                .compact();
    }

    public String getUserUidFromToken(String token) {
        return Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser().verifyWith(key).build().parseSignedClaims(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public long getAuthExpiration() {
        return authExpiration;
    }

    public long getRefreshExpiration() {
        return refreshExpiration;
    }
}
