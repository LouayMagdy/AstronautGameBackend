package com.example.astronautgamebackend.Controller.GameController;

import com.example.astronautgamebackend.UserAndRegisteration.ModelDB.model.RankingUser;
import com.example.astronautgamebackend.UserAndRegisteration.ModelDB.repo.RankRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/auth/astronaut-game/stats")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class GameController {
    @Autowired
    private RankRepository rankRepository;
    @GetMapping()
    public List<RankingUser> getRankings(Authentication authentication){
        String userName = authentication.getName();
        System.out.println(userName + "........");
        return rankRepository.findByOrderByAvgCollectedFoodDescAvgLifeDesc();
    }
}
