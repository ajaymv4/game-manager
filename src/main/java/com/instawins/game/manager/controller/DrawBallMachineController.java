package com.instawins.game.manager.controller;

import com.instawins.game.manager.dto.GameDetail;
import com.instawins.game.manager.dto.GameResult;
import com.instawins.game.manager.service.DrawBallMachineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class DrawBallMachineController {

    @Autowired
    private DrawBallMachineService service;

    @GetMapping("/draw/numbers")
    public List<GameResult> drawBall() {
        return service.selectWinners();
    }

}
