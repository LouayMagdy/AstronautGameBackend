package com.example.astronautgamebackend.UserAndRegisteration.RegisterationService;

import com.example.astronautgamebackend.JsonFilesReaderWriter.FileParser;
import com.example.astronautgamebackend.JsonFilesReaderWriter.FileWriter;
import com.example.astronautgamebackend.JsonParserWriter.userSerializer.CustomUserSerializer;
import com.example.astronautgamebackend.UserAndRegisteration.RegisterationService.Append.AppendIIMode;
import com.example.astronautgamebackend.UserAndRegisteration.RegisterationService.Append.AppendMode;
import com.example.astronautgamebackend.UserAndRegisteration.RegisterationService.Append.IMode;
import com.example.astronautgamebackend.UserAndRegisteration.RegisterationService.Checking.ICheckerMode;
import com.example.astronautgamebackend.UserAndRegisteration.RegisterationService.Checking.SignInMode;
import com.example.astronautgamebackend.UserAndRegisteration.RegisterationService.Checking.SignUpMode;
import com.example.astronautgamebackend.UserAndRegisteration.model.NormalUser;

import java.util.List;

public class Registerer implements IRegisterer{
    private static Registerer registerer;
    private Registerer(){}
    public static Registerer getInstance(){
        if (registerer == null) registerer = new Registerer();
        return registerer;
    }

    @Override
    public int signUp(NormalUser user) {
        ICheckerMode mode = SignUpMode.getInstance();
        int index = mode.doesExist(user);
        if (index == -1){
            IMode appendMode = AppendIIMode.getInstance(AppendMode.getInstance());
            return appendMode.doFunction(user);
        }
        return -1;
    }

    @Override
    public int signIn(NormalUser user) {
        ICheckerMode modeIn = new SignInMode(SignUpMode.getInstance());
        int index = modeIn.doesExist(user);
        if (index == -1) return -1;
        if (index == -2){
            IMode appendMode = AppendMode.getInstance();
            return appendMode.doFunction(user);
        }
        return user.getID();
    }

    @Override
    public void logOut(NormalUser user) {
        ICheckerMode modeIn = new SignInMode(null);
        int index = modeIn.doesExist(user);
        if (index != -1){
            List<NormalUser> list = FileParser.parseUsersFile("LoggedUsers", null);
            list.remove(user);
            FileWriter.writeTo(list, "LoggedUsers", new CustomUserSerializer());
        }
    }

    @Override
    public boolean isLoggedIn(NormalUser user) {
        try {
            return FileParser.parseUsersFile("LoggedUsers", null).contains(user);
        }
        catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
}
