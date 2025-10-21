package com.example.auth_demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/analyst")
public class AnalystController {

    @GetMapping("/report")
    public Map<String, String> analystReport() {
        return Map.of("message", "Analyst logged in successfully");
    }
}
