package com.example.demo.security;

import com.azure.identity.DefaultAzureCredentialBuilder;
import com.azure.security.keyvault.secrets.*;
import org.springframework.stereotype.Service;

@Service
public class SecretService {

    private final SecretClient client;

    public SecretService() {
        this.client = new SecretClientBuilder()
                .vaultUrl("https://demo-keyvault-jai-2026.vault.azure.net/")
                .credential(new DefaultAzureCredentialBuilder().build())
                .buildClient();
    }

    public String getJwtSecret() {
        return client.getSecret("jwt-secret").getValue();
    }
}