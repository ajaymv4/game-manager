package com.instawins.game.manager.dao;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * GAME_ID NUMBER AUTO_INCREMENT,
 * GAME_TYPE_ID NUMBER,
 * GAME_DATE DATE not null default CURRENT_TIMESTAMP,
 * FIRST_POS VARCHAR2(30),
 * SECOND_POS VARCHAR2(30),
 * THIRD_POS VARCHAR2(30),
 * GAME_STATUS VARCHAR2(30),
 * CONSTRAINT PK_GAME_ID Primary Key (GAME_ID),
 * CONSTRAINT FK_GAME_TYPE_ID  FOREIGN KEY (GAME_TYPE_ID) REFERENCES GAME_ROOM_TYPE(GAME_ROOM_TYPE_ID),
 * CONSTRAINT FK_GAME_STATUS  FOREIGN KEY (GAME_STATUS) REFERENCES GAME_STATUS_TYPE(GAME_STATUS)
 */
@Entity
public class GameInfo {

    @Id
    @GeneratedValue
    private UUID gameId;

    private Date gameDate;

    private String firstPos;

    private String secondPos;

    private String thirdPos;

    private String gameStatus;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "gameRoomTypeId")
    private GameRoomType gameRoomType;


    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "PLAYER_GAME",
            joinColumns = {@JoinColumn(name = "gameId")},
            inverseJoinColumns = {@JoinColumn(name = "tokenId")}
    )
    private List<PlayerGameInfo> playerGameInfo;


    @Override
    public String toString() {
        return String.format("Game [" +
                "gameId=%s, gameDate=%s ,firstPos=%s," +
                "secondPos=%s, thirdPos=%s, gameStatusType=%s, gameRoomType=%s ]", gameId, gameDate, firstPos, secondPos, thirdPos, gameStatus, gameRoomType);
    }

    public UUID getGameId() {
        return gameId;
    }

    public GameInfo setGameId(UUID gameId) {
        this.gameId = gameId;
        return this;
    }

    public Date getGameDate() {
        return gameDate;
    }

    public GameInfo setGameDate(Date gameDate) {
        this.gameDate = gameDate;
        return this;
    }

    public String getFirstPos() {
        return firstPos;
    }

    public GameInfo setFirstPos(String firstPos) {
        this.firstPos = firstPos;
        return this;
    }

    public String getSecondPos() {
        return secondPos;
    }

    public GameInfo setSecondPos(String secondPos) {
        this.secondPos = secondPos;
        return this;
    }

    public String getThirdPos() {
        return thirdPos;
    }

    public GameInfo setThirdPos(String thirdPos) {
        this.thirdPos = thirdPos;
        return this;
    }

    public String getGameStatus() {
        return gameStatus;
    }

    public GameInfo setGameStatus(String gameStatus) {
        this.gameStatus = gameStatus;
        return this;
    }

    public GameRoomType getGameRoomType() {
        return gameRoomType;
    }

    public GameInfo setGameRoomType(GameRoomType gameRoomType) {
        this.gameRoomType = gameRoomType;
        return this;
    }

    public List<PlayerGameInfo> getPlayerGameInfo() {
        return playerGameInfo;
    }

    public void setPlayerGameInfo(List<PlayerGameInfo> playerGameInfo) {
        this.playerGameInfo = playerGameInfo;
    }
}
