package com.instawins.game.manager.service;

import com.instawins.game.manager.dao.*;
import com.instawins.game.manager.dto.GameDetail;
import com.instawins.game.manager.dto.GameRegistrationResponse;
import com.instawins.game.manager.dto.PlayerDetailResponse;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class GameRegistrationService {

    private static final Logger log = LogManager.getLogger(GameRegistrationService.class);

    @Autowired
    private GameRepo gameRepo;

    @Autowired
    private PlayerRepo playerGameRepo;

    @Autowired
    private GameRoomTypeRepo gameRoomTypeRepo;

    /**
     * Registers a player for available game room
     *
     * @param player
     * @param gameRoomTypeId
     */
    public GameRegistrationResponse registerGame(String player, int gameRoomTypeId) {
        //TODO: Add logic to check if user exists?
        //TODO: If same user wants to enter same game type.
        GameRoomType gameRoomType = getGameRoomType(gameRoomTypeId);
        List<GameInfo> gameRooms = gameRepo.findByGameRoomType(gameRoomType);

        GameInfo game = new GameInfo();

        if (CollectionUtils.isEmpty(gameRooms)) {
            //If there is no such game room
            game = createGameRoom(gameRoomType);
            addPlayerToGameRoom(player, game);

        } else {
            //If there is an existing game room
            for (GameInfo existingGameRoom : gameRooms) {
                game = existingGameRoom;
                if (existingGameRoom.getPlayerGameInfo().size() < gameRoomType.getGameRoomSize()) {
                    //Add player to existing game room
                    addPlayerToGameRoom(player, existingGameRoom);
                } else {
                    //When game room limit is exceeded , Update existing game room and create a new one
                    existingGameRoom.setGameStatus(GameStatusType.CLOSED.toString());

                    log.debug("Max players reached, creating a new game room");
                    game = createGameRoom(gameRoomType);
                    log.debug("New game created with details {}", game);

                    addPlayerToGameRoom(player, game);
                }
            }
        }

        //Prepare response
        GameRegistrationResponse response = new GameRegistrationResponse();
        GameDetail gameDetail = new GameDetail(game.getGameId(), game.getGameDate(), game.getGameStatus(), game.getGameRoomType());
        gameDetail.setTotalPlayers(game.getPlayerGameInfo().size());
        response.setGameDetails(gameDetail);

        PlayerDetailResponse playerDetails = new PlayerDetailResponse(game.getPlayerGameInfo().get(0).getPlayerId(), game.getPlayerGameInfo().get(0).getTokenId());
        response.setPlayer(playerDetails);

        return response;

    }

    private void addPlayerToGameRoom(String player, GameInfo gameRoom) {
        //Add player to game Room
        PlayerGameInfo newPlayer = new PlayerGameInfo();
        newPlayer.setPlayerId(player);
        newPlayer.setTokenId(generateTokenId(player));

        log.debug("Created a new player with details ::", newPlayer);

        List<PlayerGameInfo> players = gameRoom.getPlayerGameInfo();

        if (null == players) {
            players = new ArrayList<>();
        }

        players.add(newPlayer);

        gameRoom.setPlayerGameInfo(players);
        gameRepo.save(gameRoom);
    }

    private GameInfo createGameRoom(GameRoomType gameRoomType) {
        GameInfo newGame = new GameInfo().setGameDate(new Date()).setGameStatus(GameStatusType.OPEN.toString()).setGameRoomType(gameRoomType);
        return newGame;
    }

    private static String generateTokenId(String userName) {
        //TODO: Utilize username to create token id
        //TODO: Token Id length should be based on game room size.
        return RandomStringUtils.randomAlphanumeric(5);
    }

    private GameRoomType getGameRoomType(int gameRoomTypeId) {
        return gameRoomTypeRepo.findByGameRoomTypeId(gameRoomTypeId);
    }

}
