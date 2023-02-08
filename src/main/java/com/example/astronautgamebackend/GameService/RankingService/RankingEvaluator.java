package com.example.astronautgamebackend.GameService.RankingService;

import com.example.astronautgamebackend.Controller.config.JWTService;
import com.example.astronautgamebackend.GameService.gameLoop.IGame;
import com.example.astronautgamebackend.UserAndRegisteration.ModelDB.model.NormalUser;
import com.example.astronautgamebackend.UserAndRegisteration.ModelDB.model.RankingUser;
import com.example.astronautgamebackend.UserAndRegisteration.ModelDB.repo.RankRepository;
import com.example.astronautgamebackend.UserAndRegisteration.ModelDB.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RankingEvaluator {
    @Autowired
    private RankRepository rankRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JWTService jwtService;

    public void saveGame(IGame game){
        Score latestScore = new Score(game.getAstronaut().getLife(), game.getAstronaut().getCollectedFood(), 0);
        Score avgScore = new Score();
        try{
            String userName = jwtService.extractUserName(game.getUserToken());
            RankingUser rankingUser = this.rankRepository.findByUser_UserName(userName);
            avgScore.setAvgLife((rankingUser.getAvgLife() * rankingUser.getGamesPlayedNum() + latestScore.getAvgLife()) / (rankingUser.getGamesPlayedNum() + 1));
            avgScore.setAvgCollectedFood((rankingUser.getAvgCollectedFood() * rankingUser.getGamesPlayedNum() + latestScore.getAvgCollectedFood()) / (rankingUser.getGamesPlayedNum() + 1));
            avgScore.setGamesPlayed(rankingUser.getGamesPlayedNum() + 1);
            this.rankRepository.updateAvgLifeAndAvgCollectedFoodAndGamesPlayedNumById(
                    avgScore.getAvgLife(), avgScore.getAvgCollectedFood(), avgScore.getGamesPlayed(), rankingUser.getId());
        }catch (Exception e){
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }
}
