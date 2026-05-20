package com.team7.gameserver.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "player_progress")
public class PlayerProgress {

    @Id
    private String playerId;

    private int lastUnlockedLevel;
    private int lastPlayedLevel;
    private int totalTime;

    private String selectedCharacter;
    private String completedRecipes;

    public PlayerProgress() {
    }

    public PlayerProgress(String playerId, int lastUnlockedLevel, int lastPlayedLevel,
            int totalTime, String selectedCharacter, String completedRecipes) {
        this.playerId = playerId;
        this.lastUnlockedLevel = lastUnlockedLevel;
        this.lastPlayedLevel = lastPlayedLevel;
        this.totalTime = totalTime;
        this.selectedCharacter = selectedCharacter;
        this.completedRecipes = completedRecipes;
    }

    public String getPlayerId() {
        return playerId;
    }

    public int getLastUnlockedLevel() {
        return lastUnlockedLevel;
    }

    public int getLastPlayedLevel() {
        return lastPlayedLevel;
    }

    public int getTotalTime() {
        return totalTime;
    }

    public String getSelectedCharacter() {
        return selectedCharacter;
    }

    public String getCompletedRecipes() {
        return completedRecipes;
    }

    public void setPlayerId(String playerId) {
        this.playerId = playerId;
    }

    public void setLastUnlockedLevel(int lastUnlockedLevel) {
        this.lastUnlockedLevel = lastUnlockedLevel;
    }

    public void setLastPlayedLevel(int lastPlayedLevel) {
        this.lastPlayedLevel = lastPlayedLevel;
    }

    public void setTotalTime(int totalTime) {
        this.totalTime = totalTime;
    }

    public void setSelectedCharacter(String selectedCharacter) {
        this.selectedCharacter = selectedCharacter;
    }

    public void setCompletedRecipes(String completedRecipes) {
        this.completedRecipes = completedRecipes;
    }
}