package com.instawins.game.manager.dto;

import com.instawins.game.manager.dao.GameRoomType;

import java.util.Date;
import java.util.UUID;

public class GameDetail {
    private UUID gameId;
    private Date gameDate;
    private String firstPos;
    private String secondPos;
    private String thirdPos;
    private String gameStatusType;
    private GameRoomType gameRoomType;
    private int totalPlayers;

    public int getTotalPlayers() {
        return totalPlayers;
    }

    public void setTotalPlayers(int totalPlayers) {
        this.totalPlayers = totalPlayers;
    }

    public GameDetail() {
    }

    public GameDetail(UUID gameId, Date gameDate, String firstPos, String secondPos, String thirdPos, String gameStatusType, GameRoomType gameRoomType) {
        this.gameId = gameId;
        this.gameDate = gameDate;
        this.firstPos = firstPos;
        this.secondPos = secondPos;
        this.thirdPos = thirdPos;
        this.gameStatusType = gameStatusType;
        this.gameRoomType = gameRoomType;
    }

    public GameDetail(UUID gameId, Date gameDate, String gameStatusType, GameRoomType gameRoomType) {
        this.gameId = gameId;
        this.gameDate = gameDate;
        this.gameStatusType = gameStatusType;
        this.gameRoomType = gameRoomType;
    }

    public UUID getGameId() {
        return gameId;
    }

    public GameDetail setGameId(UUID gameId) {
        this.gameId = gameId;
        return this;
    }

    public Date getGameDate() {
        return gameDate;
    }

    public GameDetail setGameDate(Date gameDate) {
        this.gameDate = gameDate;
        return this;
    }

    public String getFirstPos() {
        return firstPos;
    }

    public GameDetail setFirstPos(String firstPos) {
        this.firstPos = firstPos;
        return this;
    }

    public String getSecondPos() {
        return secondPos;
    }

    public GameDetail setSecondPos(String secondPos) {
        this.secondPos = secondPos;
        return this;
    }

    public String getThirdPos() {
        return thirdPos;
    }

    public GameDetail setThirdPos(String thirdPos) {
        this.thirdPos = thirdPos;
        return this;
    }

    public String getGameStatusType() {
        return gameStatusType;
    }

    public GameDetail setGameStatusType(String gameStatusType) {
        this.gameStatusType = gameStatusType;
        return this;
    }

    public GameRoomType getGameRoomType() {
        return gameRoomType;
    }

    public GameDetail setGameRoomType(GameRoomType gameRoomType) {
        this.gameRoomType = gameRoomType;
        return this;
    }
}
