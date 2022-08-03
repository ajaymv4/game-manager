package com.instawins.game.manager.controller;

import com.instawins.game.manager.dto.GenericServiceResponse;
import com.instawins.game.manager.service.PlayerGameInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class PlayerGameInfoController {

    @Autowired
    private PlayerGameInfoService service;

    //Get all games for a player Id
    @GetMapping("/game/get/player")
    public GenericServiceResponse getAllGamesForPlayer(@RequestParam String playerId){
        return service.getAllGames(playerId);
    }

    //Get active games for a player Id
    @GetMapping("/game/get/open")
    public GenericServiceResponse getOpenGamesForPlayer(@RequestParam String playerId){
        return service.getOpenGames(playerId);
    }

    //Get closed games for a player Id
    @GetMapping("/game/get/closed")
    public GenericServiceResponse getClosedGamesForPlayer(@RequestParam String playerId){
        return service.getClosedGames(playerId);
    }


}
