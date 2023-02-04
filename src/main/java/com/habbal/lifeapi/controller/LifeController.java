package com.habbal.lifeapi.controller;

import com.habbal.lifeapi.dto.Life;
import com.habbal.lifeapi.lifegame.Game;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/life")
public class LifeController {

    @GetMapping
    public int[][] life(@RequestBody Life life) {
        Game game = new Game(life);
        return game.play();
    }
}
