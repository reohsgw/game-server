package com.team7.gameserver.controller;

import com.team7.gameserver.entity.PlayerProgress;
import com.team7.gameserver.repository.PlayerProgressRepository;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/progress")
public class ProgressController {

    private final PlayerProgressRepository progressRepository;

    public ProgressController(PlayerProgressRepository progressRepository) {
        this.progressRepository = progressRepository;
    }

    @PostMapping("/save")
    public Map<String, Object> saveProgress(@RequestBody PlayerProgress progress) {
        Map<String, Object> response = new HashMap<>();

        if (progress.getPlayerId() == null || progress.getPlayerId().isBlank()) {
            response.put("success", false);
            response.put("message", "Player ID is required");
            return response;
        }

        progressRepository.save(progress);

        response.put("success", true);
        response.put("message", "Progress saved successfully");
        return response;
    }

    @PostMapping("/load")
    public Map<String, Object> loadProgress(@RequestBody Map<String, String> request) {
        String playerId = request.get("playerId");

        Map<String, Object> response = new HashMap<>();

        if (playerId == null || playerId.isBlank()) {
            response.put("success", false);
            response.put("message", "Player ID is required");
            return response;
        }

        return progressRepository.findById(playerId)
                .map(progress -> {
                    response.put("success", true);
                    response.put("message", "Progress loaded successfully");
                    response.put("progress", progress);
                    return response;
                })
                .orElseGet(() -> {
                    response.put("success", false);
                    response.put("message", "No progress found");
                    return response;
                });
    }
}