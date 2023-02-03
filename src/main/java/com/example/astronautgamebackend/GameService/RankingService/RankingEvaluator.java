package com.example.astronautgamebackend.GameService.RankingService;

import com.example.astronautgamebackend.GameService.IGame;
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

    public void saveGame(IGame game){
//        Score latestScore = new Score(game.getAstronaut().getLife(), game.getAstronaut().getCollectedFood(), 0);
//        if(!this.rankRepository.existsRankingUserByUserID(game.getUserID())) return;
//        RankingUser user = this.rankRepository.findRankingUserByUserID(game.getUserID());
//        //////will be fixed with security
//        NormalUser normalUser = this.userRepository.findNormalUserByUserName("za3bolla");///
//        user.setAvgLife((user.getAvgLife() * user.getGamesPlayedNum() + latestScore.getAvgLife()) / (user.getGamesPlayedNum() + 1));
//        user.setAvgCollectedFood((user.getAvgCollectedFood() * user.getGamesPlayedNum() + latestScore.getAvgCollectedFood()) / (user.getGamesPlayedNum() + 1));
//        user.setGamesPlayedNum(user.getGamesPlayedNum() + 1);
//        this.rankRepository.updateGamesPlayedNumAndAvgLifeAndAvgCollectedFoodByUser(user.getGamesPlayedNum(), user.getAvgLife(), user.getAvgCollectedFood(), normalUser.getID());
    }
}
