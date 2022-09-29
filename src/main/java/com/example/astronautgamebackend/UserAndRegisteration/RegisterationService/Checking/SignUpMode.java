package com.example.astronautgamebackend.UserAndRegisteration.RegisterationService.Checking;

import com.example.astronautgamebackend.JsonFilesReaderWriter.FileParser;
import com.example.astronautgamebackend.UserAndRegisteration.model.NormalUser;

import java.util.List;

public class SignUpMode implements ICheckerMode{
    private static SignUpMode signUpMode;
    private SignUpMode(){}
    public static SignUpMode getInstance() {
        if(signUpMode == null) signUpMode = new SignUpMode();
        return signUpMode;
    }

    @Override
    public int doesExist(NormalUser user) {
        List<NormalUser> users = FileParser.parseUsersFile("AllUsers");
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getUserName().equals(user.getUserName()) &&
                    users.get(i).getPassword().equals(user.getPassword())) {
                user.setID(i + 1);
                return i;
            }
        }
        return -1;
    }
}
