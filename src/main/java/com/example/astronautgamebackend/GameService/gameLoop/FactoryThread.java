package com.example.astronautgamebackend.GameService.gameLoop;

import com.example.astronautgamebackend.GameService.Movables.Intrinsics.IMovIntrinsic;
import com.example.astronautgamebackend.GameService.Movables.Intrinsics.IntrinsicFood;
import com.example.astronautgamebackend.GameService.Movables.Intrinsics.IntrinsicRock;

import java.util.Random;

public class FactoryThread extends Thread{
    private final IGame game;
    private final Random random;
    public FactoryThread(IGame game){
        this.game = game;
        this.random = new Random();
    }
    @Override
    public void run(){
        setName("Factory thread is working");
        while (game.isRunning()){
            try {
                System.out.println("factory: " + this.game.getMovableCount());
                if(this.game.getMovableCount() == 25) continue;
                int x = random.nextInt(3);
                IMovIntrinsic iMovIntrinsic = (x < 1)? IntrinsicFood.getInstance() : IntrinsicRock.getIntrinsicRock();
                this.game.createMovable(iMovIntrinsic);
                System.out.println("new movable created");
                Thread.sleep(1000);
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println(e.getMessage());
            }
        }
        game.terminateGame();
    }
}
