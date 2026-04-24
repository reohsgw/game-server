package com.team7.gameserver.controller;

import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @PostMapping("/login")
    public Map<String, Object> login(@RequestBody Map<String, String> request) {
        String id = request.get("id");
        String password = request.get("password");

        Map<String, Object> response = new HashMap<>();

        if ("reo123".equals(id) && "1234".equals(password)) {
            response.put("success", true);
            response.put("message", "Login successful");
        } else {
            response.put("success", false);
            response.put("message", "Invalid ID or password");
        }

        return response;
    }

    @PostMapping("/register")
    public Map<String, Object> register(@RequestBody Map<String, String> request) {
        String id = request.get("id");
        String email = request.get("email");
        String password = request.get("password");

        Map<String, Object> response = new HashMap<>();

        if (id == null || email == null || password == null
                || id.isBlank() || email.isBlank() || password.isBlank()) {
            response.put("success", false);
            response.put("message", "All fields are required");
            return response;
        }

        response.put("success", true);
        response.put("message", "Account created successfully");
        return response;
    }
}