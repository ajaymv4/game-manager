package com.instawins.game.manager.dao;

import org.springframework.data.repository.CrudRepository;

public interface PlayerRepo extends CrudRepository<PlayerGameInfo, String> {
    PlayerGameInfo findByPlayerId(String playerId);

    public PlayerGameInfo save(PlayerGameInfo playerGameInfo);

}
