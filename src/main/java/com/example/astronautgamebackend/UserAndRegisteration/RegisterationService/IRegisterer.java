package com.example.astronautgamebackend.UserAndRegisteration.RegisterationService;

import com.example.astronautgamebackend.UserAndRegisteration.model.NormalUser;

public interface IRegisterer {
    int signUp(NormalUser user);
    int signIn(NormalUser user);
    void logOut(NormalUser user);

    boolean isLoggedIn(NormalUser user);
}
