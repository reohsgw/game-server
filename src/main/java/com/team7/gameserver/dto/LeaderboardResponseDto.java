package com.team7.gameserver.dto;

//carries leaderboard data sent from server to unity
public class LeaderboardResponseDto {

    private int rank;
    private String playerId;
    private int totalScore;

    public LeaderboardResponseDto(int rank, String playerId, int totalScore) {
        this.rank = rank;
        this.playerId = playerId;
        this.totalScore = totalScore;
    }

    public int getRank() {
        return rank;
    }

    public String getPlayerId() {
        return playerId;
    }

    public int getTotalScore() {
        return totalScore;
    }
}