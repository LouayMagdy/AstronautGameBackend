package com.example.astronautgamebackend.UserAndRegisteration.RegisterationService.Checking;

import com.example.astronautgamebackend.JsonFilesReaderWriter.FileParser;
import com.example.astronautgamebackend.UserAndRegisteration.model.NormalUser;

import java.util.List;

public class SignInMode extends WrapperMode{
    public SignInMode(ICheckerMode wrappee) {
        super(wrappee);
    }

    @Override
    public int doesExist(NormalUser user){
        int res = super.doesExist(user);
        if(res == -1) return -1;
        List<NormalUser> users = FileParser.parseUsersFile("LoggedUsers", null);
        for (int i = 0; i < users.size(); i++)
            if (users.get(i).getUserName().equals(user.getUserName()) &&
                    users.get(i).getPassword().equals(user.getPassword())) return i;
        return -2;
    }
}
