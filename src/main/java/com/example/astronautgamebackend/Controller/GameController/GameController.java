package com.example.astronautgamebackend.Controller.GameController;

import com.example.astronautgamebackend.Controller.GameController.entities.FinishedGame;
import com.example.astronautgamebackend.Controller.dto.RankingUserDto;
import com.example.astronautgamebackend.GameService.RankingService.RankingService;
import com.example.astronautgamebackend.UserAndRegisteration.ModelDB.model.RankingUser;
import com.example.astronautgamebackend.UserAndRegisteration.ModelDB.repo.RankRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/v1/auth/astronaut-game/stats")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class GameController {
    @Autowired
    private RankingService rankingService;
    @GetMapping()
    public List<RankingUserDto> getRankings(Authentication authentication){
        try{
            String userName = authentication.getName();
            return rankingService.getRankings(userName);
        }catch (Exception e){
            return rankingService.getRankings(null);
        }
    }

    @PostMapping("/save-game")
    public String saveGame(@RequestBody FinishedGame finishedGame){
        String message = this.rankingService.saveGame(finishedGame);
        System.out.println(message);
        return message;
    }
}
