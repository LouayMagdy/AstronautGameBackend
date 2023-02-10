package com.example.astronautgamebackend.GameService.Movables;

import java.util.List;

public class MovableThread extends Thread{
    private final IMovable movable;
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
            try {
                setName("movable : " + index);
    //            System.out.println("curve: " + movable.getMoverFn() + "Point" + movable.getPosition().toString());
                System.out.println("life = " + movable.getAstronaut().getLife());
                boolean result = movable.move(movable.getAstronaut());
                if(!result) Thread.yield();
                Thread.sleep(500);
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println(e.getMessage());
                if(!emptyIndices.contains(index)) emptyIndices.add(index);
                Thread.yield();
            }
        }
        if(!emptyIndices.contains(index)) emptyIndices.add(index);emptyIndices.add(index);
    }
}
