package com.instawins.game.manager.service;

import com.instawins.game.manager.GameUtil;
import com.instawins.game.manager.dao.PlayerGameInfo;
import com.instawins.game.manager.dao.PlayerRepo;
import com.instawins.game.manager.dto.GameDetail;
import com.instawins.game.manager.dto.GenericServiceResponse;
import com.instawins.game.manager.dto.PlayerGameInfoResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class PlayerGameInfoService {

    private static final Logger log = LogManager.getLogger(PlayerGameInfoService.class);

    public static final String NOT_REGISTERED_MSG = "Oops! player %s is not trying his luck today";

    @Value("${game.firstPos.percent}")
    private Integer firstPos;

    @Value("${game.secondPos.percent}")
    private Integer secondPos;

    @Value("${game.thirdPos.percent}")
    private Integer thirdPos;

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
        List<PlayerGameInfoResponse> gameDetails;
        GenericServiceResponse response = new GenericServiceResponse();

        List<PlayerGameInfo> playerGameInfos = playerGameRepo.findByPlayerId(playerId);

        gameDetails = getAllGames(playerGameInfos);
        if (!CollectionUtils.isEmpty(gameDetails)) {
            response.setPlayerGameDetails(gameDetails);
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

    private List<PlayerGameInfoResponse> getAllGames(List<PlayerGameInfo> playerGameInfos) {
        List<PlayerGameInfoResponse> playerGameInfoResponses = new ArrayList<>();
        if (!CollectionUtils.isEmpty(playerGameInfos)) {
            playerGameInfos.forEach(playerGameInfo -> playerGameInfo.getGameInfo().forEach(game -> {
                PlayerGameInfoResponse playerGameData = new PlayerGameInfoResponse();
                GameDetail gameDetail = GameUtil.getGameDetailFromGameInfo(game);
                playerGameData.setGameDetails(gameDetail);
                playerGameData.setTokenId(playerGameInfo.getTokenId());
                playerGameData = getPlayerPosAndAmount(gameDetail,playerGameData);
                playerGameInfoResponses.add(playerGameData);
            }));
        }
        return playerGameInfoResponses;
    }

    private PlayerGameInfoResponse getPlayerPosAndAmount(GameDetail game, PlayerGameInfoResponse playerData){

        if(playerData.getTokenId().equals(game.getFirstPos())){
            playerData.setPlayerPos("1st");
            playerData.setAmount((double) (game.getGameRoomType().getGameRoomAmt()*((double)firstPos/100)));
        } else if(playerData.getTokenId().equals(game.getSecondPos())){
            playerData.setPlayerPos("2nd");
            playerData.setAmount((double) (game.getGameRoomType().getGameRoomAmt()*((double)secondPos/100)));
        }else if(playerData.getTokenId().equals(game.getThirdPos())){
            playerData.setPlayerPos("3rd");
            playerData.setAmount((double) (game.getGameRoomType().getGameRoomAmt()*((double)thirdPos/100)));
        }

        return playerData;

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
