package com.example.astronautgamebackend.GameService.Movers;

import com.example.astronautgamebackend.GameService.GeometricShapes.Point;

public interface IMover {
    String generateMoverEqn(Point astronautPos, Point initialPos);
}
