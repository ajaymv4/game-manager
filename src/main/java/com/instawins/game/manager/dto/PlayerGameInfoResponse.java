package com.instawins.game.manager.dto;

public class PlayerGameInfoResponse {

    GameDetail gameDetails;

    private String playerPos;

    private Double amount;

    private String tokenId;

    public GameDetail getGameDetails() {
        return gameDetails;
    }

    public void setGameDetails(GameDetail gameDetails) {
        this.gameDetails = gameDetails;
    }

    public String getPlayerPos() {
        return playerPos;
    }

    public void setPlayerPos(String playerPos) {
        this.playerPos = playerPos;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getTokenId() {
        return tokenId;
    }

    public void setTokenId(String tokenId) {
        this.tokenId = tokenId;
    }
}
