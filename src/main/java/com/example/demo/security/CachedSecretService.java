package com.example.demo.security;

import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class CachedSecretService {

    private static final Logger log = LoggerFactory.getLogger(CachedSecretService.class);

    private final SecretService secretService;
    private String jwtSecret;

    public CachedSecretService(SecretService secretService) {
        this.secretService = secretService;
    }

    @PostConstruct
    public void init() {
        this.jwtSecret = secretService.getJwtSecret();
        log.info("JWT Secret = {}", jwtSecret);
        System.out.println("Loaded JWT secret from Key Vault");
    }

    public String getJwtSecret() {
        return jwtSecret;
    }
}