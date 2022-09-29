package com.example.astronautgamebackend.UserAndRegisteration.model;

import com.example.astronautgamebackend.GameService.IGame;

import java.util.List;

public interface IUser {
    IGame play(int iD);
    void saveGame(IGame game, int iD);
    List<String> getRanking(int iD);
}
