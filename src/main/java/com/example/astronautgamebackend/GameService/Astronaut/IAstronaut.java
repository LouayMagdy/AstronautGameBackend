package com.example.astronautgamebackend.GameService.Astronaut;

import com.example.astronautgamebackend.GameService.GeometricShapes.Circle;
import com.example.astronautgamebackend.GameService.GeometricShapes.Point;

import java.util.List;

public interface IAstronaut {
    Point getPosition();
    List<Circle> getCircles();
    void setCircles(List<Circle> circles);
    void changeLife(int change);
    int getLife();
    int getCollectedFood();
    void eat(int change);
}
