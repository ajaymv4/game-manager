package com.instawins.game.manager.dao;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface GameRoomTypeRepo extends CrudRepository<GameRoomType, String> {

    @Cacheable(value = "GameRoomTypeCache")
    List<GameRoomType> findAll();

    GameRoomType findByGameRoomTypeId(int gameRoomTypeId);
}