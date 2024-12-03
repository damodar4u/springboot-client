package com.example.client.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class AuthConfig {
    @Value("${auth.client-id}")
    private String clientId;

    @Value("${auth.client-secret}")
    private String clientSecret;

    @Value("${auth.redirect-uri}")
    private String redirectUri;

    public String getClientId() {
        return clientId;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public String getRedirectUri() {
        return redirectUri;
    }
}
