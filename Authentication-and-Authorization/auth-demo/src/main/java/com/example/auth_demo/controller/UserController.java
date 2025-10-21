package com.example.auth_demo.controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @GetMapping("/profile")
    public Map<String, String> profile(Authentication auth) {
        return Map.of(
            "whoami", auth.getName(),
            "roles", auth.getAuthorities().toString(),
            "message", "Hello USER/ANALYST/MANAGER/ADMIN"
        );
    }
}
