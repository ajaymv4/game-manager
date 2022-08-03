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
import org.springframework.util.CollectionUtils;

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
     *
     * @param playerId
     * @return
     */
    public GenericServiceResponse getAllGames(String playerId) {
        List<GameDetail> gameDetails;
        GenericServiceResponse response = new GenericServiceResponse();

        List<PlayerGameInfo> playerGameInfos = playerGameRepo.findByPlayerId(playerId);

        gameDetails = getAllGames(playerGameInfos);
        if (!CollectionUtils.isEmpty(gameDetails)) {
            response.setGameDetails(gameDetails);
        } else {
            response.setMessage(String.format(NOT_REGISTERED_MSG, playerId));
        }
        return response;
    }


    //Get active games for a player Id
    public GenericServiceResponse getOpenGames(String playerId) {
        List<GameDetail> gameDetails;
        List<PlayerGameInfo> playerGameInfos = playerGameRepo.findByPlayerId(playerId);
        gameDetails = getOpenGames(playerGameInfos);
        GenericServiceResponse response = new GenericServiceResponse();
        if (!CollectionUtils.isEmpty(gameDetails)) {
            response.setGameDetails(gameDetails);
        } else {
            response.setMessage(String.format(NOT_REGISTERED_MSG, playerId));
        }
        return response;
    }

    //Get closed games for a player Id
    public GenericServiceResponse getClosedGames(String playerId) {
        List<GameDetail> gameDetails;
        List<PlayerGameInfo> playerGameInfos = playerGameRepo.findByPlayerId(playerId);
        gameDetails = getClosedGames(playerGameInfos);
        GenericServiceResponse response = new GenericServiceResponse();
        if (!CollectionUtils.isEmpty(gameDetails)) {
            response.setGameDetails(gameDetails);
        } else {
            response.setMessage(String.format(NOT_REGISTERED_MSG, playerId));
        }
        return response;
    }

    private List<GameDetail> getAllGames(List<PlayerGameInfo> playerGameInfos) {
        List<GameDetail> gameDetails = new ArrayList<>();
        if (!CollectionUtils.isEmpty(playerGameInfos)) {
            playerGameInfos.forEach(playerGameInfo -> playerGameInfo.getGameInfo().forEach(game -> {
                playerGameInfo.getTokenId();
                gameDetails.add(GameUtil.getGameDetailFromGameInfo(game));
            }));
        }
        return gameDetails;
    }

    private List<GameDetail> getClosedGames(List<PlayerGameInfo> playerGameInfos) {
        List<GameDetail> gameDetails = new ArrayList<>();
        if (!CollectionUtils.isEmpty(playerGameInfos)) {
            playerGameInfos.forEach(playerGameInfo -> playerGameInfo.getGameInfo().forEach(game -> {
                if(GameUtil.isClosedGame(game)){
                    gameDetails.add(GameUtil.getGameDetailFromGameInfo(game));
                }
            }));
        }
        return gameDetails;
    }

    private List<GameDetail> getOpenGames(List<PlayerGameInfo> playerGameInfos) {
        List<GameDetail> gameDetails = new ArrayList<>();
        if (!CollectionUtils.isEmpty(playerGameInfos)) {
            playerGameInfos.forEach(playerGameInfo -> playerGameInfo.getGameInfo().forEach(game -> {
                if(GameUtil.isOpenGame(game)) {
                    gameDetails.add(GameUtil.getGameDetailFromGameInfo(game));
                }
            }));
        }
        return gameDetails;
    }

}
