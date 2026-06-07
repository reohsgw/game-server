package com.team7.gameserver.controller;

import com.team7.gameserver.dto.ProgressRequest;
import com.team7.gameserver.dto.UpdateCharacterRequestDto;
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
    public Map<String, Object> saveProgress(@RequestBody ProgressRequest request) {
        Map<String, Object> response = new HashMap<>();

        if (request.getPlayerId() == null || request.getPlayerId().isBlank()) {
            response.put("success", false);
            response.put("message", "Player ID is required");
            return response;
        }

        PlayerProgress progress = progressRepository
                .findById(request.getPlayerId())
                .orElse(new PlayerProgress());

        progress.setPlayerId(request.getPlayerId());

        // Keep the highest unlocked level only
        progress.setLastUnlockedLevel(
                Math.max(progress.getLastUnlockedLevel(), request.getLastUnlockedLevel())
        );

        // Keep the best score only for each recipe
        progress.setOmuriceScore(
                Math.max(progress.getOmuriceScore(), request.getOmuriceScore())
        );

        progress.setBibimbapScore(
                Math.max(progress.getBibimbapScore(), request.getBibimbapScore())
        );

        progress.setRendangScore(
                Math.max(progress.getRendangScore(), request.getRendangScore())
        );

        // Recalculate total score from saved best scores
        progress.setTotalScore(
                progress.getOmuriceScore()
                        + progress.getBibimbapScore()
                        + progress.getRendangScore()
        );

        if (request.getSelectedCharacter() != null && !request.getSelectedCharacter().isBlank()) {
            progress.setSelectedCharacter(request.getSelectedCharacter());
        }

        progressRepository.save(progress);

        response.put("success", true);
        response.put("message", "Progress saved successfully");
        response.put("progress", progress);
        return response;
    }

    @PostMapping("/load")
    public Map<String, Object> loadProgress(@RequestBody Map<String, String> request) {
        Map<String, Object> response = new HashMap<>();

        String playerId = request.get("playerId");

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

    @PostMapping("/character")
    public Map<String, Object> updateCharacter(@RequestBody UpdateCharacterRequestDto request) {
        Map<String, Object> response = new HashMap<>();

        String playerId = request.getPlayerId();
        String selectedCharacter = request.getSelectedCharacter();

        if (playerId == null || playerId.isBlank()) {
            response.put("success", false);
            response.put("message", "Player ID is required");
            return response;
        }

        if (selectedCharacter == null || selectedCharacter.isBlank()) {
            response.put("success", false);
            response.put("message", "Selected character is required");
            return response;
        }

        return progressRepository.findById(playerId)
                .map(progress -> {
                    progress.setSelectedCharacter(selectedCharacter);
                    progressRepository.save(progress);

                    response.put("success", true);
                    response.put("message", "Character updated successfully");
                    response.put("playerId", playerId);
                    response.put("selectedCharacter", selectedCharacter);
                    return response;
                })
                .orElseGet(() -> {
                    response.put("success", false);
                    response.put("message", "Player progress not found");
                    return response;
                });
    }
}