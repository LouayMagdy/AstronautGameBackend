package com.example.astronautgamebackend.GameService.Movables;

public class IntrinsicRock extends IMovIntrinsic{
    private int radius;
    private static IntrinsicRock intrinsicRock;
    private IntrinsicRock(){
        this.radius = 15; /// may be modified
    }
    public static IntrinsicRock getIntrinsicRock(){
        if(intrinsicRock == null) intrinsicRock = new IntrinsicRock();
        return intrinsicRock;
    }
    @Override
    public int addEnergy() {
        return -20;
    }

    @Override
    protected int getRadius() {
        return 15;
    }
}
