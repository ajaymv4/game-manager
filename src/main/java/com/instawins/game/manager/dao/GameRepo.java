package com.instawins.game.manager.dao;

import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.UUID;

public interface GameRepo extends CrudRepository<GameInfo, String> {

    public GameInfo save(GameInfo gameInfo);
    public GameInfo findByGameId(UUID gameId);
    public List<GameInfo> findByGameRoomType(GameRoomType gameRoomType);
    public List<GameInfo> findAll();

}
