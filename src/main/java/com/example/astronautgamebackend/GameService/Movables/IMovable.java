package com.example.astronautgamebackend.GameService.Movables;

import com.example.astronautgamebackend.GameService.Astronaut.IAstronaut;
import com.example.astronautgamebackend.GameService.GeometricShapes.Point;
import com.example.astronautgamebackend.GameService.IGame;

public interface IMovable {
    IGame getGame();
    Point getPosition();
    int addEnergy();
    IAstronaut getAstronaut();
    String getMoverFn();
    void move(IAstronaut astronaut);
    String getType(); ///for Serialization concerns
}
