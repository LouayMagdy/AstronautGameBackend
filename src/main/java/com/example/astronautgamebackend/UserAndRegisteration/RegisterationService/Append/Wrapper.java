package com.example.astronautgamebackend.UserAndRegisteration.RegisterationService.Append;

import com.example.astronautgamebackend.UserAndRegisteration.model.NormalUser;

public class Wrapper implements IMode{
    private final IMode wrapped;

    public Wrapper(IMode wrapped) {
        this.wrapped = wrapped;
    }

    @Override
    public int doFunction(NormalUser user) {
        return this.wrapped.doFunction(user);
    }
}
