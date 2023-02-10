package com.example.astronautgamebackend.GameService.RankingService;

import com.example.astronautgamebackend.Controller.GameController.entities.FinishedGame;
import com.example.astronautgamebackend.Controller.config.JWTService;
import com.example.astronautgamebackend.Controller.dto.RankingUserDto;
import com.example.astronautgamebackend.UserAndRegisteration.ModelDB.model.RankingUser;
import com.example.astronautgamebackend.UserAndRegisteration.ModelDB.repo.RankRepository;
import com.example.astronautgamebackend.UserAndRegisteration.mappers.RankingUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RankingService {

    @Autowired
    private RankRepository rankRepository;
    @Autowired
    private JWTService jwtService;

    @Autowired
    private RankingUserMapper rankingUserMapper;

    public String saveGame(FinishedGame game){
        Score latestScore = new Score(game.getLife(), game.getCollectedFood(), 0);
        try{
            String userName = jwtService.extractUserName(game.getToken());
            RankingUser rankingUser = this.rankRepository.findByUser_UserName(userName).orElseThrow();
            int avgLife = (rankingUser.getAvgLife() * rankingUser.getGamesPlayedNum() + latestScore.getLife()) / (rankingUser.getGamesPlayedNum() + 1);
            this.rankRepository.updateAvgLifeAndAvgCollectedFoodAndGamesPlayedNumById(
                    avgLife, Math.max(rankingUser.getMaxCollectedFood(), latestScore.getCollectedFood()),
                    rankingUser.getGamesPlayedNum() + 1, rankingUser.getId());
            return "Saved Successfully";
        }catch (Exception e){
//            e.printStackTrace();
            System.out.println(e.getMessage());
            return "Anonymous User";
        }
    }

    public List<RankingUserDto> getRankings(String userName){
        List<RankingUserDto> rankingUserDtos = new java.util.ArrayList<>(this.rankRepository.getTopRankings(3).stream().map(ru -> this.rankingUserMapper.map(ru)).toList());
        RankingUserDto requester = getRequester(userName);
        if (requester == null || rankingUserDtos.contains(requester)) return rankingUserDtos;
        rankingUserDtos.add(requester);
        return rankingUserDtos;
    }

    private RankingUserDto getRequester(String userName){
        try {
            RankingUser rankingUser = this.rankRepository.findByUser_UserName(userName).orElseThrow(() -> new UsernameNotFoundException("Anonymous user"));
            return this.rankingUserMapper.map(rankingUser);
        }catch (Exception e){
            return null;
        }
    }
}
