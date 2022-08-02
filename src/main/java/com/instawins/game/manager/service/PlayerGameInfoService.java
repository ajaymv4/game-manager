package com.instawins.game.manager.service;

import com.instawins.game.manager.GameUtil;
import com.instawins.game.manager.dao.PlayerGameInfo;
import com.instawins.game.manager.dao.PlayerRepo;
import com.instawins.game.manager.dto.GameDetail;
import com.instawins.game.manager.dto.GenericServiceResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PlayerGameInfoService {

    private static final Logger log = LogManager.getLogger(PlayerGameInfoService.class);

    public static final String NOT_REGISTERED_MSG = "Oops! player %s is not trying his luck today";

    @Autowired
    private PlayerRepo playerGameRepo;

    //Get all games for a player Id
    /**
     * Get games for playerId in argument
     * @param playerId
     * @return
     */
    public GenericServiceResponse getGamesForPlayer(String playerId){
        List<GameDetail> gameDetails = new ArrayList<>();
        PlayerGameInfo playerGameInfo = playerGameRepo.findByPlayerId(playerId);
        GenericServiceResponse response = new GenericServiceResponse();
        if(null!= playerGameInfo) {
            playerGameInfo.getGameInfo().forEach(game -> {
                gameDetails.add(GameUtil.getGameDetailFromGameInfo(game));
            });
            response.setGameDetails(gameDetails);
            return response;
        } else{
            response.setMessage(String.format(NOT_REGISTERED_MSG,playerId));
            return response;
        }
    }

    //Get active games for a player Id
    public GenericServiceResponse getOpenGamesForPlayer(String playerId){
        List<GameDetail> gameDetails = new ArrayList<>();
        PlayerGameInfo playerGameInfo = playerGameRepo.findByPlayerId(playerId);
        GenericServiceResponse response = new GenericServiceResponse();
        if(null!= playerGameInfo) {
            playerGameInfo.getGameInfo().forEach(game -> {
                if(GameUtil.isOpenGame(game)) {
                    gameDetails.add(GameUtil.getGameDetailFromGameInfo(game));
                }
            });
            response.setGameDetails(gameDetails);
            return response;
        } else{
            response.setMessage(String.format(NOT_REGISTERED_MSG,playerId));
            return response;
        }
    }

    //Get closed games for a player Id
    public GenericServiceResponse getClosedGamesForPlayer(String playerId){
        List<GameDetail> gameDetails = new ArrayList<>();
        PlayerGameInfo playerGameInfo = playerGameRepo.findByPlayerId(playerId);
        GenericServiceResponse response = new GenericServiceResponse();
        if(null!= playerGameInfo) {
            playerGameInfo.getGameInfo().forEach(game -> {
                if(GameUtil.isClosedGame(game)) {
                    gameDetails.add(GameUtil.getGameDetailFromGameInfo(game));
                }
            });
            response.setGameDetails(gameDetails);
            return response;
        } else{
            response.setMessage(String.format(NOT_REGISTERED_MSG,playerId));
            return response;
        }
    }


}
