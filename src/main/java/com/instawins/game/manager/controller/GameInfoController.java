package com.instawins.game.manager.controller;

import com.instawins.game.manager.dto.GameDetail;
import com.instawins.game.manager.dto.PlayerDetailResponse;
import com.instawins.game.manager.service.GameInfoService;
import com.instawins.game.manager.dto.GenericServiceResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class GameInfoController {

    @Autowired
    private GameInfoService gameInfoService;

    @GetMapping("/game/get/all")
    public List<GameDetail> getAllGames(){
        return gameInfoService.getAllGames();
    }

    @GetMapping("/game/get/id")
    public GenericServiceResponse getPlayersInGame(@RequestParam UUID gameId){
        return gameInfoService.getAllPlayersInGame(gameId);
    }

    //Get all open games

    //Get all closed games

}
