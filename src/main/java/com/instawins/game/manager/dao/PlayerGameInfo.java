package com.instawins.game.manager.dao;

import javax.persistence.*;
import java.util.List;

/**
 *  GAME_ID NUMBER NOT NULL,
 *     PLAYER_ID VARCHAR2(30) NOT NULL,
 *     TOKEN_ID VARCHAR2(30) NOT NULL,
 * 	CONSTRAINT FK_GAME_ID  FOREIGN KEY (GAME_ID) REFERENCES GAME_INFO(GAME_ID)
 */
@Entity
public class PlayerGameInfo {
    private String playerId;

    @ManyToMany(cascade=CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "playerGameInfo")
    private List<GameInfo> gameInfo;

    @Id
    private String tokenId;

    @Override
    public String toString() {
        return "PlayerGameInfo{" +
                "playerId='" + playerId + '\'' +
                ", tokenId='" + tokenId + '\'' +
                '}';
    }

    public List<GameInfo> getGameInfo() {
        return gameInfo;
    }

    public void setGameInfo(List<GameInfo> gameInfo) {
        this.gameInfo = gameInfo;
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
