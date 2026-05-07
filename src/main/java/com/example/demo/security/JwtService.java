package com.example.demo.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;

@Service
public class JwtService {
    /*    private final String SECRET = "mysecretkeymysecretkeymysecretkey12345";
        private final SecretKey key = Keys.hmacShaKeyFor(SECRET.getBytes());*/
    private static final Logger log = LoggerFactory.getLogger(JwtService.class);

    @Value("${jwt-secret:default-secret}")
    private String secret;

    @PostConstruct
    public void printSecret() {
        log.info("JWT Secret = {}", secret);
    }

    private SecretKey getKey() {

        return Keys.hmacShaKeyFor(secret.getBytes());
    }

    public String generateToken(String username) {
        return Jwts.builder()
                .subject(username)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + 600000))
                .signWith(getKey())
                .compact();
    }

    public String getUsername(String token) {
        return Jwts.parser().verifyWith(getKey()).build()
                .parseSignedClaims(token).getPayload().getSubject();
    }
}
