package com.example.astronautgamebackend.GameService.Movables.Intrinsics;

import com.example.astronautgamebackend.GameService.GeometricShapes.Point;
import com.example.astronautgamebackend.GameService.gameLoop.IGame;
import com.example.astronautgamebackend.GameService.Movables.XChanger.XChanger;

public class IntrinsicRock extends IMovIntrinsic {
    private int radius;
    private static IntrinsicRock intrinsicRock;

    private IntrinsicRock() {
        this.radius = 15; /// may be modified
    }

    public static IntrinsicRock getIntrinsicRock() {
        if (intrinsicRock == null) intrinsicRock = new IntrinsicRock();
        return intrinsicRock;
    }

    @Override
    public int addEnergy() {
        return -20;
    }

    @Override
    protected int getRadius() {
        return 17;
    }

    @Override
    public synchronized Point move(IGame game, String moverFunction, XChanger xChanger, Point currPos) {
        currPos = super.move(game, moverFunction, xChanger, currPos);
        if (super.isCollided && game.getAstronaut().getLife() <= 0) game.terminateGame();
        return currPos;
    }
}