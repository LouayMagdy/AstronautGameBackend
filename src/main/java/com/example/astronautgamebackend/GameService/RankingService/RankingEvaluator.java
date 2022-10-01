package com.example.astronautgamebackend.GameService.RankingService;

import com.example.astronautgamebackend.GameService.IGame;
import com.example.astronautgamebackend.JsonFilesReaderWriter.FileParser;
import com.example.astronautgamebackend.JsonFilesReaderWriter.FileWriter;
import com.example.astronautgamebackend.JsonParserWriter.UserDeSerializer.RankingDeserializer;
import com.example.astronautgamebackend.JsonParserWriter.userSerializer.CustomRankingSerializer;
import com.example.astronautgamebackend.UserAndRegisteration.model.NormalUser;

import java.util.List;

public class RankingEvaluator {
    private static RankingEvaluator instance;
    private RankingEvaluator(){}

    public static RankingEvaluator getInstance() {
        if (instance == null) instance = new RankingEvaluator();
        return instance;
    }
    public void saveGame(IGame game){
        Score latestScore = new Score(game.getAstronaut().getLife(), game.getAstronaut().getCollectedFood(), 0);
        List<NormalUser> rankings = FileParser.parseUsersFile("RankingUsers", new RankingDeserializer());
        int index = 0;
        for (;index < rankings.size(); index++)
            if (rankings.get(index).getID() == game.getUserID())break;
        NormalUser user = rankings.get(index);
        rankings.remove(index);
        Score avgScore = user.getScore();
        avgScore.setAvgLife((avgScore.getAvgLife() * avgScore.getGamePlayed() + latestScore.getAvgLife()) / (avgScore.getGamePlayed() + 1));
        avgScore.setAvgCollectedFood((avgScore.getAvgCollectedFood() * avgScore.getGamePlayed() + latestScore.getAvgCollectedFood()) / (avgScore.getGamePlayed() + 1));
        avgScore.setGamePlayed(avgScore.getGamePlayed() + 1);
        int left = 0, right = rankings.size() - 1, medium = 0;
        while (left <= right){
            medium = left + (right - left) / 2;
            if (rankings.get(medium).getScore().getAvgLife() > avgScore.getAvgLife() ||
                (rankings.get(medium).getScore().getAvgLife() == avgScore.getAvgLife() &&
                 rankings.get(medium).getScore().getAvgCollectedFood() > avgScore.getAvgCollectedFood())) right = medium - 1;
            else if (rankings.get(medium).getScore().getAvgLife() < avgScore.getAvgLife() ||
                    (rankings.get(medium).getScore().getAvgLife() == avgScore.getAvgLife() &&
                    rankings.get(medium).getScore().getAvgCollectedFood() < avgScore.getAvgCollectedFood())) left = medium + 1;
            else break;
        }
        user.setScore(avgScore);
        System.out.println(user.toString());
        if (rankings.size() > 0 && rankings.get(medium).getID() != user.getID()) rankings.add(medium, user);
        else if (rankings.size() == 0) rankings.add(user);
        FileWriter.writeTo(rankings, "RankingUsers", new CustomRankingSerializer());
    }
}
