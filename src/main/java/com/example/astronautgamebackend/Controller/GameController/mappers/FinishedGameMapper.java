package com.example.astronautgamebackend.Controller.GameController.mappers;

import com.example.astronautgamebackend.Controller.GameController.entities.FinishedGame;
import com.example.astronautgamebackend.GameService.gameLoop.IGame;

public class FinishedGameMapper {
    public FinishedGame map(IGame game){
        return new FinishedGame(game.getUserToken(), game.getAstronaut().getCollectedFood(), game.getAstronaut().getLife());
    }
}
