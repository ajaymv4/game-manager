package com.instawins.game.manager.dao;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface GameRoomTypeRepo extends CrudRepository<GameRoomType, String> {

    List<GameRoomType> findAll();

    GameRoomType findByGameRoomTypeId(int gameRoomTypeId);
}