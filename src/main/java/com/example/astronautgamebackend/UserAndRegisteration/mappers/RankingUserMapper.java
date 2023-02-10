package com.example.astronautgamebackend.UserAndRegisteration.mappers;

import com.example.astronautgamebackend.Controller.dto.RankingUserDto;
import com.example.astronautgamebackend.UserAndRegisteration.ModelDB.model.RankingUser;
import org.springframework.stereotype.Service;

@Service
public class RankingUserMapper {
    public RankingUserDto map(RankingUser rankingUser){
        return new RankingUserDto(rankingUser.getUser().getUsername(),
                rankingUser.getMaxCollectedFood(), rankingUser.getAvgLife(), rankingUser.getGamesPlayedNum());
    }
}
