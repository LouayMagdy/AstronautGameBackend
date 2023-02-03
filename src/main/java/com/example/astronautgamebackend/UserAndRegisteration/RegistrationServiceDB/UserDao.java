package com.example.astronautgamebackend.UserAndRegisteration.RegistrationServiceDB;

import com.example.astronautgamebackend.UserAndRegisteration.ModelDB.model.NormalUser;
import com.example.astronautgamebackend.UserAndRegisteration.ModelDB.model.RankingUser;
import com.example.astronautgamebackend.UserAndRegisteration.ModelDB.repo.RankRepository;
import com.example.astronautgamebackend.UserAndRegisteration.ModelDB.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserDao implements IUserRegisterer{
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RankRepository rankRepository;

    public boolean addUser(NormalUser user){
        boolean isFound = isUserFound(user);
        if (!isFound) {
            this.userRepository.save(user);
            RankingUser rankingUser = new RankingUser(getUserByUserName(user),0, 0, 0);
            this.rankRepository.save(rankingUser);
        }
        return !isFound;
    }

    private boolean isUserFound(NormalUser user){
        return this.userRepository.existsNormalUsersByUserName(user.getUserName());
    }

    private NormalUser getUserByUserName(NormalUser user){
        return this.userRepository.findNormalUserByUserName(user.getUserName());
    }
}
