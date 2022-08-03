package com.instawins.game.manager.dto;

import java.util.Map;
import java.util.UUID;

public class GameResult {

    private UUID gameId;
    private Map<String,String> winnerTokens;

    public UUID getGameId() {
        return gameId;
    }

    public void setGameId(UUID gameId) {
        this.gameId = gameId;
    }

    public Map<String, String> getWinnerTokens() {
        return winnerTokens;
    }

    public void setWinnerTokens(Map<String, String> winnerTokens) {
        this.winnerTokens = winnerTokens;
    }
}
