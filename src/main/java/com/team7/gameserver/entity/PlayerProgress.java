package com.team7.gameserver.entity;

import jakarta.persistence.*;

//represents a players game progress - maps to the "player_progress" table
@Entity
@Table(name = "player_progress")
public class PlayerProgress {

    @Id
    private String playerId; // referencing users.id

    private int lastUnlockedLevel;

    private int omuriceScore;
    private int bibimbapScore;
    private int rendangScore;
    private int totalScore; // the sum of all three recipe scores

    private String selectedCharacter; // currently selected character

    public PlayerProgress() {
    }

    public PlayerProgress(
            String playerId,
            int lastUnlockedLevel,
            int omuriceScore,
            int bibimbapScore,
            int rendangScore,
            int totalScore,
            String selectedCharacter) {
        this.playerId = playerId;
        this.lastUnlockedLevel = lastUnlockedLevel;
        this.omuriceScore = omuriceScore;
        this.bibimbapScore = bibimbapScore;
        this.rendangScore = rendangScore;
        this.totalScore = totalScore;
        this.selectedCharacter = selectedCharacter;
    }

    public String getPlayerId() {
        return playerId;
    }

    public int getLastUnlockedLevel() {
        return lastUnlockedLevel;
    }

    public int getOmuriceScore() {
        return omuriceScore;
    }

    public int getBibimbapScore() {
        return bibimbapScore;
    }

    public int getRendangScore() {
        return rendangScore;
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

    public void setOmuriceScore(int omuriceScore) {
        this.omuriceScore = omuriceScore;
    }

    public void setBibimbapScore(int bibimbapScore) {
        this.bibimbapScore = bibimbapScore;
    }

    public void setRendangScore(int rendangScore) {
        this.rendangScore = rendangScore;
    }

    public void setTotalScore(int totalScore) {
        this.totalScore = totalScore;
    }

    public void setSelectedCharacter(String selectedCharacter) {
        this.selectedCharacter = selectedCharacter;
    }
}