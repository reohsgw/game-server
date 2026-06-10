package com.team7.gameserver.controller;

import com.team7.gameserver.entity.User;
import com.team7.gameserver.repository.UserRepository;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import com.team7.gameserver.entity.PlayerProgress;
import com.team7.gameserver.repository.PlayerProgressRepository;

import java.util.HashMap;
import java.util.Map;

//handles player registration and login
@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserRepository userRepository;
    private final PlayerProgressRepository progressRepository;

    private final BCryptPasswordEncoder passwordEncoder;

    
    public AuthController(UserRepository userRepository,
            PlayerProgressRepository progressRepository,
            BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.progressRepository = progressRepository;
        this.passwordEncoder = passwordEncoder;
    }

    //registers a new player — creates a user record and a default progress record
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

        //check duplicate id and email 
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
        
        //hash the password before saving to database
        String hashedPassword = passwordEncoder.encode(password);
        User user = new User(id, email, hashedPassword);
        userRepository.save(user);

        //create a default progress record for the new player
        PlayerProgress defaultProgress = new PlayerProgress();
        defaultProgress.setPlayerId(id);
        defaultProgress.setLastUnlockedLevel(1);
        defaultProgress.setOmuriceScore(0);
        defaultProgress.setBibimbapScore(0);
        defaultProgress.setRendangScore(0);
        defaultProgress.setTotalScore(0);
        defaultProgress.setSelectedCharacter("chef_01");

        progressRepository.save(defaultProgress);

        response.put("success", true);
        response.put("message", "Account created successfully");
        return response;
    }

    //verify player input login info and return result
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
                    if (passwordEncoder.matches(password, user.getPassword())) {
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