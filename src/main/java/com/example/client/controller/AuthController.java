package com.example.client.controller;

import com.example.client.config.AuthConfig;
import com.example.authlib.servlets.LoginServlet;
import com.example.authlib.servlets.AuthCallbackServlet;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
public class AuthController {

    private final AuthConfig authConfig;

    public AuthController(AuthConfig authConfig) {
        this.authConfig = authConfig;
    }

    @GetMapping("/login")
    public void login(HttpServletRequest request, HttpServletResponse response) throws Exception {
        new LoginServlet(authConfig).doGet(request, response);
    }

    @GetMapping("/callback")
    public void callback(@RequestParam("code") String code, HttpServletRequest request, HttpServletResponse response) throws Exception {
        new AuthCallbackServlet(authConfig).handleCallback(code, request, response);
    }
}
