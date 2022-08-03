package com.instawins.game.manager.dao;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.UUID;

public interface GameRepo extends CrudRepository<GameInfo, String> {

    public GameInfo save(GameInfo gameInfo);
    public GameInfo findByGameId(UUID gameId);
    @Cacheable(value = "GameCache")
    public List<GameInfo> findByGameRoomTypeAndGameStatus(GameRoomType gameRoomType,String gameStatus);
    public List<GameInfo> findByGameStatus(String gameStatus);
    @Cacheable(value = "GameCache")
    public List<GameInfo> findAll();

}
