package com.example.astronautgamebackend.GameService.Movables;

import com.example.astronautgamebackend.GameService.Astronaut.IAstronaut;
import com.example.astronautgamebackend.GameService.GeometricShapes.Point;
import com.example.astronautgamebackend.GameService.IGame;
import com.example.astronautgamebackend.GameService.Movables.Intrinsics.IMovIntrinsic;
import com.example.astronautgamebackend.GameService.Movables.Intrinsics.IntrinsicRock;
import com.example.astronautgamebackend.GameService.Movables.XChanger.XChanger;
import com.example.astronautgamebackend.GameService.Movers.IMover;

public class Movable implements IMovable{
    private final IMover mover;
    private Point point;
    private final IMovIntrinsic iMovIntrinsic;
    private final IGame game;

    private final XChanger xChanger;

    public Movable(IMover mover, Point point, IMovIntrinsic iMovIntrinsic, IGame game, XChanger xChanger) {
        this.mover = mover;
        this.point = point;
        this.iMovIntrinsic = iMovIntrinsic;
        this.game = game;
        this.xChanger = xChanger;
    }

    @Override
    public IGame getGame() {
        return this.game;
    }

    @Override
    public Point getPosition() {
        return this.point;
    }
    @Override
    public int addEnergy() {
        return iMovIntrinsic.addEnergy();
    }
    @Override
    public IAstronaut getAstronaut() {return game.getAstronaut();}
    @Override
    public String getMoverFn() {
        return mover.generateMoverEqn(this.getAstronaut().getPosition(), this.point);
    }

    @Override
    public boolean move(IAstronaut astronaut) {
        this.point = this.iMovIntrinsic.move(this.game, this.getMoverFn(), this.xChanger, this.point);
        return this.point.getX() != -1 && this.point.getY() != -1;
    }
    @Override
    public String getType() {
        return (iMovIntrinsic instanceof IntrinsicRock)? "rock" : "food";
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Movable movable = (Movable) o;
        return point.equals(movable.point) && this.getType().equals(movable.getType());
    }

}
