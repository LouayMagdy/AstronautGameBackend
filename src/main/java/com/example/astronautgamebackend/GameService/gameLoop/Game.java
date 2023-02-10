package com.example.astronautgamebackend.GameService.gameLoop;

import com.example.astronautgamebackend.GameService.Astronaut.IAstronaut;
import com.example.astronautgamebackend.GameService.GeometricShapes.Point;
import com.example.astronautgamebackend.GameService.Movables.IMovable;
import com.example.astronautgamebackend.GameService.Movables.Intrinsics.IMovIntrinsic;
import com.example.astronautgamebackend.GameService.Movables.Movable;
import com.example.astronautgamebackend.GameService.Movables.MovableThread;
import com.example.astronautgamebackend.GameService.Movables.XChanger.XChanger;
import com.example.astronautgamebackend.GameService.Movables.XChanger.XDecrementer;
import com.example.astronautgamebackend.GameService.Movables.XChanger.XIncrementer;
import com.example.astronautgamebackend.GameService.Movers.IMover;
import com.example.astronautgamebackend.GameService.Movers.LinearMover;
import com.example.astronautgamebackend.GameService.Movers.QuadraticMover;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


@Setter
@Getter
public class Game implements IGame{
    private String userToken;
    private IAstronaut astronaut;
    private int width;
    private int height;
    private boolean isRunning;
    private final Random random;
    private List<IMovable> movables;
    private List<Integer> emptyIndices;

    public Game(int width, int height, IAstronaut astronaut, String userToken) {
        this.userToken = userToken;
        this.width = width;
        this.height = height;
        this.isRunning = true;
        this.movables = new ArrayList<>();
        this.emptyIndices = new ArrayList<>();
        this.astronaut = astronaut;
        this.random = new Random();
    }

    @Override
    public void play() {
        FactoryThread factoryThread = new FactoryThread(this);
        factoryThread.start();
    }

    @Override
    synchronized public void createMovable(IMovIntrinsic intrinsic) {
        int x = random.nextInt(), y = random.nextInt();
        IMover mover = (x % 2 == 0)? QuadraticMover.getQuadraticMover() : LinearMover.getLinearMover();
        Point initialPos = new Point((y % 2 == 0)? 0 : this.width, random.nextInt(this.height));
        XChanger xChanger = (y % 2 == 0)? new XIncrementer() : new XDecrementer();
        IMovable movable = new Movable(mover, initialPos, intrinsic, this, xChanger);
        if (!emptyIndices.isEmpty()){
            y = emptyIndices.get(0);
            movables.set(y, movable);
            emptyIndices.remove(0);
        }
        else {
            y = movables.size();
            movables.add(movable);
        }
//        for (int i = 0; i < movables.size(); i++) System.out.println(movables.get(i).getPosition().toString());
        MovableThread thread = new MovableThread(movable, this.emptyIndices, y);
        thread.start();
    }

    @Override
    public int getMovableCount() {return movables.size() - emptyIndices.size();}
    @Override
    public void setDimensions(int w, int h) {
        this.width = w;
        this.height = h;
    }

    @Override
    public void terminateGame(){this.isRunning = false;}
}
