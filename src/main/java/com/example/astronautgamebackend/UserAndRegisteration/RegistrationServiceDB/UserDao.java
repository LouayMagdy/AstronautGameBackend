package com.example.astronautgamebackend.UserAndRegisteration.RegistrationServiceDB;

import com.example.astronautgamebackend.Controller.config.JWTService;
import com.example.astronautgamebackend.Controller.dto.LoggingUser;
import com.example.astronautgamebackend.Controller.dto.NormalUserDto;
import com.example.astronautgamebackend.UserAndRegisteration.ModelDB.model.NormalUser;
import com.example.astronautgamebackend.UserAndRegisteration.ModelDB.model.RankingUser;
import com.example.astronautgamebackend.UserAndRegisteration.ModelDB.repo.RankRepository;
import com.example.astronautgamebackend.UserAndRegisteration.ModelDB.repo.UserRepository;
import com.example.astronautgamebackend.UserAndRegisteration.mappers.NormalUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class UserDao implements IUserRegisterer{
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RankRepository rankRepository;

    @Autowired
    private NormalUserMapper normalUserMapper;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JWTService jwtService;

    public boolean addUser(NormalUserDto userDto){
        try {
            NormalUser user = this.normalUserMapper.map(userDto);
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
        return this.userRepository.findNormalUserByUserName(user.getUsername()).orElseThrow(() -> new UsernameNotFoundException("user not found"));
    }

    public String authenticate(LoggingUser loggingUser){
        try{
            System.out.println(loggingUser.getUserName() + "...." + loggingUser.getPassword());
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loggingUser.getUserName(), loggingUser.getPassword()));
            System.out.println(loggingUser.getUserName() + "...." + loggingUser.getPassword());
            var user = this.userRepository.findNormalUserByUserName(loggingUser.getUserName()).orElseThrow();
            return jwtService.generateToken(user.getUsername());
        }catch (Exception e){
            e.printStackTrace();
            System.out.println(e.getMessage());
            return null;
        }
    }
}
