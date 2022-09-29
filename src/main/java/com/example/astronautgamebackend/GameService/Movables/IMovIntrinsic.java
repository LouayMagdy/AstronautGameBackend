package com.example.astronautgamebackend.GameService.Movables;

import com.example.astronautgamebackend.GameService.GeometricShapes.Circle;
import com.example.astronautgamebackend.GameService.GeometricShapes.Point;
import com.example.astronautgamebackend.GameService.IGame;
import com.example.astronautgamebackend.GameService.Movers.Evaluator;

import java.util.List;

public abstract class IMovIntrinsic {
    protected boolean isCollided;
    public abstract int addEnergy();
    protected abstract int getRadius();
    protected void doesCollide(boolean isCollided){
        this.isCollided = isCollided;
    }
    synchronized public Point move(IGame game, String moverFunction, XChanger xChanger, Point currPos) {
        int x = xChanger.change(currPos.getX());
        currPos.setCoordinates(x, (int)Math.ceil(Evaluator.eval(moverFunction, x)));
        List<Circle> astronaut = game.getAstronaut().getCircles();
        for (Circle c: astronaut) {
            if (c.getRadius() + this.getRadius() > Evaluator.getDistance(new Point(c.getX(), c.getY()), currPos)) {
                game.getAstronaut().setLife(this.addEnergy());
                doesCollide(true);
                break;
            }
        }
        return currPos;
    }
}
