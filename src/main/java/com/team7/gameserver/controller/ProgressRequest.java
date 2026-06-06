package com.team7.gameserver.controller;

public class ProgressRequest {
    private String playerId;
    private int lastUnlockedLevel;
    private int omuriceScore;
    private int bibimbapScore;
    private int rendangScore;
    private int totalScore;
    private String selectedCharacter;

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