package com.team7.gameserver.controller;

import com.team7.gameserver.entity.User;
import com.team7.gameserver.repository.UserRepository;
import org.springframework.web.bind.annotation.*;
import com.team7.gameserver.entity.PlayerProgress;
import com.team7.gameserver.repository.PlayerProgressRepository;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserRepository userRepository;
    private final PlayerProgressRepository progressRepository;

    public AuthController(UserRepository userRepository, PlayerProgressRepository progressRepository) {
        this.userRepository = userRepository;
        this.progressRepository = progressRepository;
    }

    @PostMapping("/register")
    public Map<String, Object> register(@RequestBody Map<String, String> request) {
        String id = request.get("id");
        String email = request.get("email");
        String password = request.get("password");

        Map<String, Object> response = new HashMap<>();

        if (id == null || email == null || password == null ||
                id.isBlank() || email.isBlank() || password.isBlank()) {
            response.put("success", false);
            response.put("message", "All fields are required");
            return response;
        }

        if (userRepository.existsById(id)) {
            response.put("success", false);
            response.put("message", "ID already exists");
            return response;
        }

        if (userRepository.existsByEmail(email)) {
            response.put("success", false);
            response.put("message", "Email already exists");
            return response;
        }

        User user = new User(id, email, password);
        userRepository.save(user);
        PlayerProgress defaultProgress = new PlayerProgress();
        defaultProgress.setPlayerId(id);
        defaultProgress.setLastUnlockedLevel(1);
        defaultProgress.setTotalScore(0);
        defaultProgress.setSelectedCharacter("chef_01");

        progressRepository.save(defaultProgress);

        response.put("success", true);
        response.put("message", "Account created successfully");
        return response;
    }

    @PostMapping("/login")
    public Map<String, Object> login(@RequestBody Map<String, String> request) {
        String id = request.get("id");
        String password = request.get("password");

        Map<String, Object> response = new HashMap<>();

        if (id == null || password == null || id.isBlank() || password.isBlank()) {
            response.put("success", false);
            response.put("message", "ID and password are required");
            return response;
        }

        return userRepository.findById(id)
                .map(user -> {
                    if (user.getPassword().equals(password)) {
                        response.put("success", true);
                        response.put("message", "Login successful");
                    } else {
                        response.put("success", false);
                        response.put("message", "Invalid ID or password");
                    }
                    return response;
                })
                .orElseGet(() -> {
                    response.put("success", false);
                    response.put("message", "Invalid ID or password");
                    return response;
                });
    }
}