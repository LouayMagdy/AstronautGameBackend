package com.example.astronautgamebackend.UserAndRegisteration.RegistrationServiceDB;

import com.example.astronautgamebackend.Controller.dto.LoggingUser;
import com.example.astronautgamebackend.Controller.dto.NormalUserDto;

public interface IUserRegisterer {
    boolean addUser(NormalUserDto user);
    String authenticate(LoggingUser user);
}
