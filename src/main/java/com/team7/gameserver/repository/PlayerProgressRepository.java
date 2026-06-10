package com.team7.gameserver.repository;

import com.team7.gameserver.entity.PlayerProgress;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

//repository for accessing the player_progress table
public interface PlayerProgressRepository extends JpaRepository<PlayerProgress, String> {
    List<PlayerProgress> findAllByOrderByTotalScoreDesc(); // returns all player progress records sorted by total score
                                                           // descending(for leaderboard)
}