package com.example.astronautgamebackend.GameService.Movables.XChanger;

public class XDecrementer implements XChanger{
    @Override
    public int change(int x) {
        return x - 2;
    }
}
