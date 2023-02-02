package com.example.astronautgamebackend.GameService;

import com.example.astronautgamebackend.GameService.Astronaut.IAstronaut;
import com.example.astronautgamebackend.GameService.Movables.Intrinsics.IMovIntrinsic;
import com.example.astronautgamebackend.GameService.Movables.IMovable;

import java.util.List;

public interface IGame {
    void createMovable(IMovIntrinsic intrinsic); // thread of more priority

    int getMovableCount();

    void play();
    IAstronaut getAstronaut();

    void setAstronaut(IAstronaut astronaut);

    void setDimensions(int w, int h);
    int getWidth();
    int getHeight();
    boolean isRunning();
    void terminateGame();
    List<IMovable> getMovables();
    List<Integer> getEmptyIndices();
    int getUserID();
    void setId(int id);
}
