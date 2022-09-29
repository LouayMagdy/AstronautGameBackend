package com.example.astronautgamebackend.GameService.Movables;

import com.example.astronautgamebackend.GameService.GeometricShapes.Point;
import com.example.astronautgamebackend.GameService.IGame;

public class IntrinsicFood extends IMovIntrinsic{
    private static IntrinsicFood food;
    private IntrinsicFood(){}
    public static IntrinsicFood getInstance() {
        if (food == null) food = new IntrinsicFood();
        return food;
    }
    @Override
    public int addEnergy() {
        return 5;
    }

    @Override
    protected int getRadius() {
        return 10;
    }

    @Override
    public synchronized Point move(IGame game, String moverFunction, XChanger xChanger, Point currPos) {
        currPos = super.move(game, moverFunction, xChanger, currPos);
        if (super.isCollided) {
            game.getAstronaut().setCollectedFood(1);
            currPos = new Point(-1, -1);
        }
        super.doesCollide(false);
        return currPos;
    }
}
