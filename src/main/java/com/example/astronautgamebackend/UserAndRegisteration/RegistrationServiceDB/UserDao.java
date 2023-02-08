package com.example.astronautgamebackend.UserAndRegisteration.RegistrationServiceDB;

import com.example.astronautgamebackend.Controller.RegiterationController.dto.NormalUserDto;
import com.example.astronautgamebackend.UserAndRegisteration.ModelDB.model.NormalUser;
import com.example.astronautgamebackend.UserAndRegisteration.ModelDB.model.RankingUser;
import com.example.astronautgamebackend.UserAndRegisteration.ModelDB.repo.RankRepository;
import com.example.astronautgamebackend.UserAndRegisteration.ModelDB.repo.UserRepository;
import com.example.astronautgamebackend.UserAndRegisteration.mappers.NormalUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserDao implements IUserRegisterer{
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RankRepository rankRepository;

    public boolean addUser(NormalUserDto userDto){
        try {
            NormalUser user = NormalUserMapper.map(userDto);
            boolean isFound = isUserFound(user);
            if (!isFound) {
                this.userRepository.save(user);
                RankingUser rankingUser = new RankingUser(getUserByUserName(user),0, 0, 0);
                this.rankRepository.save(rankingUser);
            }
            return !isFound;
        }catch (Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    private boolean isUserFound(NormalUser user){
        return this.userRepository.existsNormalUsersByUserName(user.getUsername());
    }

    private NormalUser getUserByUserName(NormalUser user){
        return this.userRepository.findNormalUserByUserName(user.getUsername());
    }
}
