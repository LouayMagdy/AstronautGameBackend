package com.example.astronautgamebackend.Controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class RankingUserDto {
    private String userName;
    private int maxCollectedFood;
    private int avgLife;
    private int gamesPlayedNum;
}
