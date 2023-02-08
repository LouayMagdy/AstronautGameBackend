package com.example.astronautgamebackend.UserAndRegisteration.RegistrationServiceDB;

import com.example.astronautgamebackend.Controller.RegiterationController.dto.NormalUserDto;
import com.example.astronautgamebackend.UserAndRegisteration.ModelDB.model.NormalUser;

public interface IUserRegisterer {
    boolean addUser(NormalUserDto user);
}
