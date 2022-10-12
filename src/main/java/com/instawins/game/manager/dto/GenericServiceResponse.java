package com.instawins.game.manager.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.instawins.game.manager.dto.GameDetail;

import java.util.List;

public class GenericServiceResponse {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<GameDetail> gameDetails;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<PlayerGameInfoResponse> playerGameDetails;

   private GameDetail gameDetail;
    private String message;

    public void setGameDetail(GameDetail gameDetail) {
        this.gameDetail = gameDetail;
    }

    private List<PlayerDetailResponse> playerDetail;

    public GameDetail getGameDetail() {
        return gameDetail;
    }

    public List<GameDetail> getGameDetails() {
        return gameDetails;
    }

    public void setGameDetails(List<GameDetail> gameDetails) {
        this.gameDetails = gameDetails;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<PlayerDetailResponse> getPlayerDetail() {
        return playerDetail;
    }

    public void setPlayerDetail(List<PlayerDetailResponse> playerDetail) {
        this.playerDetail = playerDetail;
    }

    public List<PlayerGameInfoResponse> getPlayerGameDetails() {
        return playerGameDetails;
    }

    public void setPlayerGameDetails(List<PlayerGameInfoResponse> playerGameDetails) {
        this.playerGameDetails = playerGameDetails;
    }
}
