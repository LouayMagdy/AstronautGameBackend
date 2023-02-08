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
}
