package com.example.astronautgamebackend.GameService.Movables;

import com.example.astronautgamebackend.GameService.Astronaut.IAstronaut;
import com.example.astronautgamebackend.GameService.GeometricShapes.Point;
import com.example.astronautgamebackend.GameService.gameLoop.IGame;

public interface IMovable {
    IGame getGame();
    Point getPosition();

    void setPosition(int x, int y);
    int addEnergy();
    IAstronaut getAstronaut();
    String getMoverFn();
    boolean move(IAstronaut astronaut);
    String getType(); ///for Serialization concerns
    boolean equals(Object o);
}
