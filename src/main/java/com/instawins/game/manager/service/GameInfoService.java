package com.instawins.game.manager.service;

import com.instawins.game.manager.GameUtil;
import com.instawins.game.manager.dao.GameInfo;
import com.instawins.game.manager.dao.GameRepo;
import com.instawins.game.manager.dao.PlayerGameInfo;
import com.instawins.game.manager.dao.PlayerRepo;
import com.instawins.game.manager.dto.GameDetail;
import com.instawins.game.manager.dto.GenericServiceResponse;
import com.instawins.game.manager.dto.PlayerDetailResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class GameInfoService {

    private static final Logger log = LogManager.getLogger(GameInfoService.class);

    @Autowired
    private GameRepo gameRepo;

    /**
     * Get all the games from backend.
     * @return
     */
    public List<GameDetail> getAllGames(){
        List<GameInfo> games = gameRepo.findAll();
        List<GameDetail> gameDetails = new ArrayList<>();
        games.forEach(game ->{
                gameDetails.add(GameUtil.getGameDetailFromGameInfo(game));
        });
        return gameDetails;
    }

    /**
     * Returns details of a Game along with list of players
     * @param gameId
     * @return
     */
    public GenericServiceResponse getAllPlayersInGame(UUID gameId) {
        GenericServiceResponse response = new GenericServiceResponse();
        List<PlayerDetailResponse> playersList = new ArrayList<>();
        GameInfo game = gameRepo.findByGameId(gameId);
        List<PlayerGameInfo> players = game.getPlayerGameInfo();
        players.forEach(player -> {
            log.debug(player);
            PlayerDetailResponse player1 = new PlayerDetailResponse(player.getPlayerId(), player.getTokenId());
            playersList.add(player1);
        });
        response.setPlayerDetail(playersList);
        response.setGameDetail(GameUtil.getGameDetailFromGameInfo(game));
        return response;
    }

}
