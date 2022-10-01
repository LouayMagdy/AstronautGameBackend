package com.example.astronautgamebackend.GameService;

import com.example.astronautgamebackend.GameService.Astronaut.Astronaut;
import com.example.astronautgamebackend.GameService.Astronaut.IAstronaut;
import com.example.astronautgamebackend.GameService.GeometricShapes.Circle;
import com.example.astronautgamebackend.GameService.GeometricShapes.Point;
import com.example.astronautgamebackend.GameService.Movables.*;
import com.example.astronautgamebackend.GameService.Movers.IMover;
import com.example.astronautgamebackend.GameService.Movers.LinearMover;
import com.example.astronautgamebackend.GameService.Movers.QuadraticMover;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Game implements IGame{
    private int userId;
    private IAstronaut astronaut;
    private int width;
    private int height;
    private boolean isRunning;
    private List<IMovable> movables;
    private List<Integer> emptyIndices;

    public Game(int width, int height, IAstronaut astronaut, int Id) {
        this.userId = Id;
        this.width = width;
        this.height = height;
        this.isRunning = true;
        this.movables = new ArrayList<>();
        this.emptyIndices = new ArrayList<>();
        this.astronaut = astronaut;
    }

    @Override
    public void play() {
        FactoryThread thread = new FactoryThread(this);
        thread.start();
    }

    @Override
    synchronized public void createMovable(IMovIntrinsic intrinsic) {
        Random random = new Random();
        int x = random.nextInt();
        int y = random.nextInt();
        IMover mover = (x % 2 == 0)? QuadraticMover.getQuadraticMover() : LinearMover.getLinearMover();
        Point initialPos = new Point((y % 2 == 0)? 0 : this.width, random.nextInt(this.height));
        XChanger xChanger = (y % 2 == 0)? new XIncrementer() : new XDecrementer();
        IMovable movable = new Movable(mover, initialPos, intrinsic, this, xChanger);
        if (emptyIndices.size() != 0){
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
    public IAstronaut getAstronaut() {return this.astronaut;}

    @Override
    public void setAstronaut(IAstronaut astronaut) {
        this.astronaut = astronaut;
    }

    @Override
    public int getWidth() {return this.width;}
    @Override
    public int getHeight() {return this.height;}
    @Override
    public boolean isRunning() {return this.isRunning;}

    @Override
    public void setDimensions(int w, int h) {
        this.width = w;
        this.height = h;
    }
    @Override
    public List<IMovable> getMovables(){return this.movables;}

    @Override
    public List<Integer> getEmptyIndices(){return this.emptyIndices;}

    @Override
    public void terminateGame(){this.isRunning = false;}

    @Override
    public int getUserID() {return this.userId;}

    @Override
    public void setId(int id) {this.userId = id;}
}
