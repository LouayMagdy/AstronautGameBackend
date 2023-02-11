package com.example.astronautgamebackend.Controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Setter
@Getter
@AllArgsConstructor
public class RankingUserDto {
    private String userName;
    private int maxCollectedFood;
    private int avgLife;
    private int gamesPlayedNum;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RankingUserDto that = (RankingUserDto) o;
        return Objects.equals(userName, that.userName);
    }
}
