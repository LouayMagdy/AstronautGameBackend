package com.example.astronautgamebackend.GameService.Astronaut;

import com.example.astronautgamebackend.GameService.GeometricShapes.Circle;
import com.example.astronautgamebackend.GameService.GeometricShapes.Point;

import java.util.List;

public class Astronaut implements IAstronaut{
    private List<Circle> circles;
    private int life;
    private int collectedFood;

    public Astronaut(List<Circle> circles) {
        this.circles = circles;
        this.life = 100;
        this.collectedFood = 0;
    }

    public Astronaut(List<Circle> circles, int life, int collectedFood) {
        this.circles = circles;
        this.life = life;
        this.collectedFood = collectedFood;
    }
    @Override
    public void setCircles(List<Circle> circles) {this.circles = circles;}
    @Override
    public List<Circle> getCircles() {return circles;}

    @Override
    public Point getPosition() {
        if(circles.isEmpty()) return new Point(0, 0);
        return new Point(circles.get(1).getX(), circles.get(1).getY());
    }

    @Override
    public void changeLife(int change) {
        this.life += change;
        this.life = Math.min(100, Math.max(0, this.life));
    }
    @Override
    public int getLife() {return life;}

    @Override
    public int getCollectedFood() {return this.collectedFood;}
    @Override
    public void eat(int change) {this.collectedFood += change;}
}
