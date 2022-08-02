package com.instawins.game.manager.dto;

public class GameRegistrationResponse {

    private PlayerDetailResponse player;
    private GameDetail gameDetail;

    public PlayerDetailResponse getPlayer() {
        return player;
    }

    public void setPlayer(PlayerDetailResponse player) {
        this.player = player;
    }

    public GameDetail getGameDetails() {
        return gameDetail;
    }

    public void setGameDetails(GameDetail gameDetail) {
        this.gameDetail = gameDetail;
    }
}
