package com.instawins.game.manager.controller;


import com.instawins.game.manager.dto.GameDetail;
import com.instawins.game.manager.dto.GameRegistrationResponse;
import com.instawins.game.manager.dto.PlayerDetailResponse;
import com.instawins.game.manager.service.GameRegistrationService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@CrossOrigin
@RestController
@RequestMapping("/api")
@Log4j2
public class GameRegistrationController {

    @Autowired
    private GameRegistrationService service;

    @PostMapping("/register/user")
    public GameRegistrationResponse registerGame(@RequestParam String user, @RequestParam int gameTypeId){
        return service.registerGame(user,gameTypeId);
    }

}
