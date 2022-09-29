package com.example.astronautgamebackend.GameService.Movables;

public class XIncrementer implements XChanger{
    @Override
    public int change(int x) {
        return x + 2;
    }
}
