package com.example.astronautgamebackend.UserAndRegisteration.RegisterationService.Checking;

import com.example.astronautgamebackend.UserAndRegisteration.model.NormalUser;

public class WrapperMode implements ICheckerMode{
    private ICheckerMode wrappee;
    public WrapperMode(ICheckerMode wrappee) {
        this.wrappee = wrappee;
    }

    @Override
    public int doesExist(NormalUser user) {
        if(this.wrappee == null) return 0;
        return this.wrappee.doesExist(user);
    }
}
