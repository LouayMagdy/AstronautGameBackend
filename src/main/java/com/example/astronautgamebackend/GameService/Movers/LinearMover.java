package com.example.astronautgamebackend.GameService.Movers;

import com.example.astronautgamebackend.GameService.GeometricShapes.Point;

import java.util.ArrayList;
import java.util.List;

public class LinearMover implements IMover{
    private static LinearMover linearMover;
    private LinearMover(){}
    public static LinearMover getLinearMover() {
        if (linearMover == null) linearMover = new LinearMover();
        return linearMover;
    }
    @Override
    public String generateMoverEqn(Point astronautPos, Point initialPos) {
        List<Point> points = new ArrayList<>();
        points.add(astronautPos);
        points.add(initialPos);
        return NewtonInterpolationService.generateEqn(points);
    }
}
