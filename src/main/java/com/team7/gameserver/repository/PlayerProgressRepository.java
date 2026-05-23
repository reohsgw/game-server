package com.team7.gameserver.repository;

import com.team7.gameserver.entity.PlayerProgress;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface PlayerProgressRepository extends JpaRepository<PlayerProgress, String> {
    List<PlayerProgress> findAllByOrderByTotalScoreDesc();
}