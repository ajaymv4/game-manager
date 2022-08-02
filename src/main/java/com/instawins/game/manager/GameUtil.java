package com.instawins.game.manager;

import com.instawins.game.manager.dao.GameInfo;
import com.instawins.game.manager.dao.GameStatusType;
import com.instawins.game.manager.dto.GameDetail;
import org.springframework.util.CollectionUtils;

public class GameUtil {
    public static GameDetail getGameDetailFromGameInfo(GameInfo game) {
        GameDetail gameDetail = new GameDetail(game.getGameId(), game.getGameDate(),
                game.getFirstPos(), game.getSecondPos(), game.getThirdPos(),game.getGameStatus(), game.getGameRoomType());
        if(!CollectionUtils.isEmpty(game.getPlayerGameInfo())) {
            gameDetail.setTotalPlayers(game.getPlayerGameInfo().size());
        }
        return gameDetail;
    }

    public static boolean isOpenGame(GameInfo game) {
        return GameStatusType.OPEN.toString().equals(game.getGameStatus());
    }
    public static boolean isClosedGame(GameInfo game) {
        return GameStatusType.CLOSED.toString().equals(game.getGameStatus());
    }

}
