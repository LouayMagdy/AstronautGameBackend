package com.example.astronautgamebackend.UserAndRegisteration.RegisterationService.Checking;

import com.example.astronautgamebackend.UserAndRegisteration.model.NormalUser;

public interface ICheckerMode {
    int doesExist(NormalUser user);
}
