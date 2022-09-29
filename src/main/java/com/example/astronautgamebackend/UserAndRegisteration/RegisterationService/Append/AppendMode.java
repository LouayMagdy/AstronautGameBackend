package com.example.astronautgamebackend.UserAndRegisteration.RegisterationService.Append;

import com.example.astronautgamebackend.JsonFilesReaderWriter.FileParser;
import com.example.astronautgamebackend.JsonFilesReaderWriter.FileWriter;
import com.example.astronautgamebackend.UserAndRegisteration.model.NormalUser;

import java.util.List;

public class AppendMode implements IMode{
    @Override
    public int doFunction(NormalUser user) {
        List<NormalUser> users = FileParser.parseUsersFile("LoggedUsers");
        users.add(user);
        FileWriter.writeTo(users, "LoggedUsers");
        return user.getID();
    }
}
