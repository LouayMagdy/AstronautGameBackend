package com.example.astronautgamebackend.GameService.Movables;

public class XDecrementer implements XChanger{
    @Override
    public int change(int x) {
        return x - 3;
    }
}
