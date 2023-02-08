package com.example.astronautgamebackend.GameService.RankingService;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Setter
@Getter
public class Score {
    private int avgLife;
    private int avgCollectedFood;
    private int gamesPlayed;

    public Score() {
        this.avgLife = 0;
        this.avgCollectedFood = 0;
        this.gamesPlayed = 0;
    }
}
