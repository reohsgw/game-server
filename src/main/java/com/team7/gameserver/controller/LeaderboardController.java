package com.team7.gameserver.controller;

import com.team7.gameserver.dto.LeaderboardResponseDto;
import com.team7.gameserver.entity.PlayerProgress;
import com.team7.gameserver.repository.PlayerProgressRepository;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

//returns all players ranked by total score for the leaderboard screen
@RestController
@RequestMapping("/leaderboard")
@CrossOrigin(origins = "*")
public class LeaderboardController {

    private final PlayerProgressRepository progressRepository;

    public LeaderboardController(PlayerProgressRepository progressRepository) {
        this.progressRepository = progressRepository;
    }

    // fetch all player progress records ordered by total score (highest first)
    @GetMapping
    public List<LeaderboardResponseDto> getLeaderboard() {
        List<PlayerProgress> progressList = progressRepository.findAllByOrderByTotalScoreDesc();

        List<LeaderboardResponseDto> leaderboard = new ArrayList<>();

        int rank = 1;

        for (PlayerProgress progress : progressList) {
            leaderboard.add(new LeaderboardResponseDto(
                    rank,
                    progress.getPlayerId(),
                    progress.getTotalScore()));

            rank++;
        }

        return leaderboard;
    }
}