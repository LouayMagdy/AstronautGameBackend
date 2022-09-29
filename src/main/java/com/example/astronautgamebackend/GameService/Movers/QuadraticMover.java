package com.example.astronautgamebackend.GameService.Movers;

import com.example.astronautgamebackend.GameService.GeometricShapes.Point;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class QuadraticMover implements IMover{
    private static QuadraticMover quadraticMover;
    private QuadraticMover(){}
    public static QuadraticMover getQuadraticMover() {
        if (quadraticMover == null) quadraticMover = new QuadraticMover();
        return quadraticMover;
    }

    @Override
    public String generateMoverEqn(Point astronautPos, Point initialPos) {
        Point mini = new Point(Math.min(astronautPos.getX(), initialPos.getX()), Math.min(astronautPos.getY(), initialPos.getY()));
        Point maxi = new Point(Math.max(astronautPos.getX(), initialPos.getX()), Math.max(astronautPos.getY(), initialPos.getY()));
        Random random = new Random();
        int x = mini.getX() + random.nextInt(maxi.getX() - mini.getX());
        int y = mini.getY() + random.nextInt(maxi.getY() - mini.getY());
        List<Point> points = new ArrayList<>();
        points.add(astronautPos);
        points.add(new Point(x, y));
        points.add(initialPos);
        return NewtonInterpolationService.generateEqn(points);
    }
}
