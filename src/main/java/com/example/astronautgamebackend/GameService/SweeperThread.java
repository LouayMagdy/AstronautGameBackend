package com.example.astronautgamebackend.GameService;

import com.example.astronautgamebackend.GameService.Movables.IMovable;

import java.util.ArrayList;
import java.util.List;

public class SweeperThread extends Thread{
    private List<String> movableList;
    private final IGame game;

    public SweeperThread(IGame game){
        this.game = game;
    }

    @Override
    public void run(){
        while (game.isRunning()){
            List<IMovable> movables = game.getMovables();
            String toCompare;
            for (int i = 0; i < movables.size(); i++) {
                toCompare = movables.get(i).getType() + ", " + movables.get(i).getPosition();
                if(movableList.contains(toCompare) && !game.getEmptyIndices().contains(i))
                    game.getEmptyIndices().add(i);

            }
            movableList = new ArrayList<>(); movables = game.getMovables();
            for (IMovable movable : movables) {
                toCompare = movable.getType() + ", " + movable.getPosition();
                movableList.add(toCompare);
            }
            try {
                Thread.sleep(3500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
