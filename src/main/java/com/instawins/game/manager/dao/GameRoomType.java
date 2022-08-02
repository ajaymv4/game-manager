package com.instawins.game.manager.dao;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * GAME_ROOM_TYPE_ID NUMBER NOT NULL,
 *     GAME_ROOM_AMT NUMERIC(20)  NOT NULL,
 *     GAME_ROOM_SIZE NUMERIC(20)  NOT NULL,
 *     GAME_DESC VARCHAR2(100)  NOT NULL,
 */

@Entity
public class GameRoomType {

    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    private int gameRoomTypeId;
    private Integer gameRoomAmt;
    private Integer gameRoomSize;
    private String gameDesc;

    public int getGameRoomTypeId() {
        return gameRoomTypeId;
    }

    public void setGameRoomTypeId(int gameRoomTypeId) {
        this.gameRoomTypeId = gameRoomTypeId;
    }

    public Integer getGameRoomAmt() {
        return gameRoomAmt;
    }

    public void setGameRoomAmt(Integer gameRoomAmt) {
        this.gameRoomAmt = gameRoomAmt;
    }

    public Integer getGameRoomSize() {
        return gameRoomSize;
    }

    public void setGameRoomSize(Integer gameRoomSize) {
        this.gameRoomSize = gameRoomSize;
    }

    public String getGameDesc() {
        return gameDesc;
    }

    public void setGameDesc(String gameDesc) {
        this.gameDesc = gameDesc;
    }
}
