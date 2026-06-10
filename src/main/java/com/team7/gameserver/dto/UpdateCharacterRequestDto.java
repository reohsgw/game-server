package com.team7.gameserver.dto;

//carries the character update request from unity
public class UpdateCharacterRequestDto {

    private String playerId;
    private String selectedCharacter;

    public UpdateCharacterRequestDto() {
    }

    public String getPlayerId() {
        return playerId;
    }

    public String getSelectedCharacter() {
        return selectedCharacter;
    }

    public void setPlayerId(String playerId) {
        this.playerId = playerId;
    }

    public void setSelectedCharacter(String selectedCharacter) {
        this.selectedCharacter = selectedCharacter;
    }
}