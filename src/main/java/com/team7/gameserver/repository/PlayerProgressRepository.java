package com.team7.gameserver.repository;

import com.team7.gameserver.entity.PlayerProgress;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerProgressRepository extends JpaRepository<PlayerProgress, String> {
}