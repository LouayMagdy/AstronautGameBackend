package com.example.astronautgamebackend.GameService.Movables.Intrinsics;

import com.example.astronautgamebackend.GameService.GeometricShapes.Circle;
import com.example.astronautgamebackend.GameService.GeometricShapes.Point;
import com.example.astronautgamebackend.GameService.IGame;
import com.example.astronautgamebackend.GameService.Movables.XChanger.XChanger;
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
        currPos.setCoordinates(x, (int)Math.ceil(Evaluator.eval(moverFunction, x, currPos.getY())));
        List<Circle> astronaut = game.getAstronaut().getCircles();
        for (Circle c: astronaut) {
            if (c.getRadius() + this.getRadius() > Evaluator.getDistance(new Point(c.getX(), c.getY()), currPos)) {
                game.getAstronaut().changeLife(this.addEnergy());
                doesCollide(true);
                currPos = new Point(-1, -1);
                break;
            }
        }
        return currPos;
    }
}
