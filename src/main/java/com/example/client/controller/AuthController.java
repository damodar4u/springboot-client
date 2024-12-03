package com.example.client.controller;

import com.example.client.config.AuthConfig;
import com.example.authlib.utils.TokenUtils;
import com.auth0.jwt.interfaces.Claim;
import com.microsoft.aad.msal4j.IAuthenticationResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class AuthController {

    private final AuthConfig authConfig = new AuthConfig(
            "your-client-id",
            "your-client-secret",
            "https://localhost:8443/callback"
    );

    @GetMapping("/login")
    public String login() {
        // Generate login URL
        String loginUrl = "https://login.microsoftonline.com/{tenant}/oauth2/v2.0/authorize" +
                "?client_id=" + authConfig.getClientId() +
                "&response_type=code" +
                "&redirect_uri=" + authConfig.getRedirectUri() +
                "&scope=openid profile email";
        return "Login URL: " + loginUrl;
    }

    @GetMapping("/callback")
    public Map<String, Claim> callback(@RequestParam("code") String code) throws Exception {
        // Exchange auth code for token
        IAuthenticationResult result = TokenUtils.acquireTokenWithAuthCode(code, authConfig);

        // Extract and return claims
        return TokenUtils.extractClaims(result.idToken());
    }
}
