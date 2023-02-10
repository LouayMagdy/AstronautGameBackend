package com.example.astronautgamebackend.GameService.RankingService;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Setter
@Getter
public class Score {
    private int life;
    private int collectedFood;
    private int gamesPlayed;

    public Score() {
        this.life = 0;
        this.collectedFood = 0;
        this.gamesPlayed = 0;
    }
}
