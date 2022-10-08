package com.example.astronautgamebackend.GameService.Movables;

import java.util.List;

public class MovableThread extends Thread{
    private IMovable movable;
    private final List<Integer> emptyIndices;
    private final int index;

    public MovableThread(IMovable movable, List<Integer> emptyIndices, int index){
        this.movable = movable;
        this.emptyIndices = emptyIndices;
        this.index = index;
    }
    @Override
    public void run(){
        while (movable.getPosition().getX() > -1 && movable.getPosition().getX() <= movable.getGame().getWidth() &&
                movable.getPosition().getY() > -1 && movable.getPosition().getY() <= movable.getGame().getHeight() &&
                movable.getGame().isRunning()){
//            System.out.println("curve: " + movable.getMoverFn() + "Point" + movable.getPosition().toString());
            System.out.println("life = " + movable.getAstronaut().getLife());
            movable.move(movable.getAstronaut());
            try {
                Thread.sleep(600);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        emptyIndices.add(index);
    }
}
