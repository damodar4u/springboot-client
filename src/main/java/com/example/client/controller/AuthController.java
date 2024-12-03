package com.example.client.controller;

import com.example.client.config.AuthConfig;
import com.example.authlib.servlets.LoginServlet;
import com.example.authlib.servlets.AuthCallbackServlet;
import com.auth0.jwt.interfaces.Claim;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@RestController
public class AuthController {

    private final AuthConfig authConfig = new AuthConfig(
            "your-client-id",
            "your-client-secret",
            "https://localhost:8443/callback"
    );

    @GetMapping("/login")
    public void login(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // Delegate login functionality to the JAR
        new LoginServlet(authConfig).doGet(request, response);
    }

    @GetMapping("/callback")
    public Map<String, Claim> callback(@RequestParam("code") String code, HttpServletRequest request, HttpServletResponse response) throws Exception {
        // Delegate callback processing to the JAR
        return new AuthCallbackServlet(authConfig).handleCallback(code, request, response);
    }
}
