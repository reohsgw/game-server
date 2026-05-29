package com.team7.gameserver.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "player_progress")
public class PlayerProgress {

    @Id
    private String playerId;

    private int lastUnlockedLevel;
    private int totalScore;
    private String selectedCharacter;

    public PlayerProgress() {
    }

    public PlayerProgress(String playerId, int lastUnlockedLevel, int totalScore, String selectedCharacter) {
        this.playerId = playerId;
        this.lastUnlockedLevel = lastUnlockedLevel;
        this.totalScore = totalScore;
        this.selectedCharacter = selectedCharacter;
    }

    public String getPlayerId() {
        return playerId;
    }

    public int getLastUnlockedLevel() {
        return lastUnlockedLevel;
    }

    public int getTotalScore() {
        return totalScore;
    }

    public String getSelectedCharacter() {
        return selectedCharacter;
    }

    public void setPlayerId(String playerId) {
        this.playerId = playerId;
    }

    public void setLastUnlockedLevel(int lastUnlockedLevel) {
        this.lastUnlockedLevel = lastUnlockedLevel;
    }

    public void setTotalScore(int totalScore) {
        this.totalScore = totalScore;
    }

    public void setSelectedCharacter(String selectedCharacter) {
        this.selectedCharacter = selectedCharacter;
    }
}