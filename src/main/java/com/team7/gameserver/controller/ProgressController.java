//HASEGAWA REO
package com.team7.gameserver.controller;

import com.team7.gameserver.dto.ProgressRequest;
import com.team7.gameserver.dto.UpdateCharacterRequestDto;
import com.team7.gameserver.entity.PlayerProgress;
import com.team7.gameserver.repository.PlayerProgressRepository;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

//handles saving, loading, and updating player progress
@RestController
@RequestMapping("/progress")
public class ProgressController {

    private final PlayerProgressRepository progressRepository;

    public ProgressController(PlayerProgressRepository progressRepository) {
        this.progressRepository = progressRepository;
    }

    // save player progress, always keep the highest score per recipe
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

        // Only update if the new value is higher than what is already saved
        progress.setLastUnlockedLevel(
                Math.max(progress.getLastUnlockedLevel(), request.getLastUnlockedLevel()));

        progress.setOmuriceScore(
                Math.max(progress.getOmuriceScore(), request.getOmuriceScore()));

        progress.setBibimbapScore(
                Math.max(progress.getBibimbapScore(), request.getBibimbapScore()));

        progress.setRendangScore(
                Math.max(progress.getRendangScore(), request.getRendangScore()));

        // recalculate total score just to make sure its correct
        progress.setTotalScore(
                progress.getOmuriceScore()
                        + progress.getBibimbapScore()
                        + progress.getRendangScore());

        // only update character if a value was provided
        if (request.getSelectedCharacter() != null && !request.getSelectedCharacter().isBlank()) {
            progress.setSelectedCharacter(request.getSelectedCharacter());
        }

        progressRepository.save(progress);

        response.put("success", true);
        response.put("message", "Progress saved successfully");
        response.put("progress", progress);
        return response;
    }

    // load player progress by playerId
    @GetMapping("/load/{playerId}")
    public Map<String, Object> loadProgress(@PathVariable String playerId) {
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

    // updates only the selected character for a player
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