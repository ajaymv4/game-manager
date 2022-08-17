package com.instawins.game.manager.dto;

public class PlayerDetailResponse {

    private String playerId;

    private String tokenId;

    public PlayerDetailResponse(String playerId, String tokenId) {
        this.playerId = playerId;
        this.tokenId = tokenId;
    }

    public PlayerDetailResponse(String playerId) {
        this.playerId = playerId;
    }

    public PlayerDetailResponse(String playerId, String tokenId, GameDetail gameDetails) {
        this.playerId = playerId;
        this.tokenId = tokenId;
    }

    public String getPlayerId() {
        return playerId;
    }

    public void setPlayerId(String playerId) {
        this.playerId = playerId;
    }

    public String getTokenId() {
        return tokenId;
    }

    public void setTokenId(String tokenId) {
        this.tokenId = tokenId;
    }
}
