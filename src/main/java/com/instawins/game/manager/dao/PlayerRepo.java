package com.instawins.game.manager.dao;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PlayerRepo extends CrudRepository<PlayerGameInfo, String> {
    List<PlayerGameInfo> findByPlayerId(String playerId);

    public PlayerGameInfo save(PlayerGameInfo playerGameInfo);

}
