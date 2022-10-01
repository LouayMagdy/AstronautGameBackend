package com.example.astronautgamebackend.GameService;

import com.example.astronautgamebackend.GameService.Movables.IMovIntrinsic;
import com.example.astronautgamebackend.GameService.Movables.IntrinsicFood;
import com.example.astronautgamebackend.GameService.Movables.IntrinsicRock;

import java.util.Random;

public class FactoryThread extends Thread{
    private final long creationTime;
    private IGame game;
    public FactoryThread(IGame game){
        this.game = game;
        this.creationTime = System.currentTimeMillis();
    }
    @Override
    public void run(){
        Random random = new Random();
        while (System.currentTimeMillis() - creationTime <= 1000 * 120 && game.isRunning()){
            if(this.game.getMovableCount() == 25) continue;
            int x = random.nextInt(3);
            IMovIntrinsic iMovIntrinsic = (x < 1)? IntrinsicFood.getInstance() : IntrinsicRock.getIntrinsicRock();
            this.game.createMovable(iMovIntrinsic);
            System.out.println("new movable created");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        game.terminateGame();
    }
}
