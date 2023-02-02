package com.example.astronautgamebackend.GameService.Movables.Intrinsics;

import com.example.astronautgamebackend.GameService.GeometricShapes.Point;
import com.example.astronautgamebackend.GameService.IGame;
import com.example.astronautgamebackend.GameService.Movables.XChanger.XChanger;

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
        return 17;
    }

    @Override
    public synchronized Point move(IGame game, String moverFunction, XChanger xChanger, Point currPos) {
        currPos = super.move(game, moverFunction, xChanger, currPos);
        if (super.isCollided) game.getAstronaut().eat(1);
        super.doesCollide(false);
        return currPos;
    }
}
