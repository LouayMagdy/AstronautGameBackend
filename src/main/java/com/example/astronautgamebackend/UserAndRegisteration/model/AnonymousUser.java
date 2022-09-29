package com.example.astronautgamebackend.UserAndRegisteration.model;

import com.example.astronautgamebackend.GameService.IGame;

import java.util.List;

public class AnonymousUser implements IUser{
    @Override
    public IGame play(int iD) {
        return null;
    }

    @Override
    public void saveGame(IGame game, int iD) {

    }

    @Override
    public List<String> getRanking(int iD) {
        return null;
    }
}
