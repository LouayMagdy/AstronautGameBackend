package com.example.astronautgamebackend.GameService.RankingService;

public class Score {
    private int avgLife;
    private int avgCollectedFood;
    private int gamesPlayed;

//    public Score(int avgLife, int avgCollectedFood) {
//        this.avgLife = avgLife;
//        this.avgCollectedFood = avgCollectedFood;
//    }

    public Score(int avgLife, int avgCollectedFood, int gamePlayed) {
        this.avgLife = avgLife;
        this.avgCollectedFood = avgCollectedFood;
        this.gamesPlayed = gamePlayed;
    }
    public int getAvgLife() {
        return avgLife;
    }

    public void setAvgLife(int avgLife) {
        this.avgLife = avgLife;
    }

    public int getAvgCollectedFood() {
        return avgCollectedFood;
    }

    public void setAvgCollectedFood(int avgCollectedFood) {
        this.avgCollectedFood = avgCollectedFood;
    }

    public int getGamePlayed() {
        return gamesPlayed;
    }

    public void setGamePlayed(int gamePlayed) {
        this.gamesPlayed = gamePlayed;
    }
}
