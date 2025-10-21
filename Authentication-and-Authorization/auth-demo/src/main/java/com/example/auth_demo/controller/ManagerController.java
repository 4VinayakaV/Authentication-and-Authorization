package com.example.auth_demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/manager")
public class ManagerController {

    @GetMapping("/report")
    public Map<String, String> managerreport() {
        return Map.of("message", "manager logged in successfully");
    }
}

