package com.example.astronautgamebackend.UserAndRegisteration.RegisterationService.Append;

import com.example.astronautgamebackend.JsonFilesReaderWriter.FileParser;
import com.example.astronautgamebackend.JsonFilesReaderWriter.FileWriter;
import com.example.astronautgamebackend.JsonParserWriter.userSerializer.CustomUserSerializer;
import com.example.astronautgamebackend.UserAndRegisteration.model.NormalUser;

import java.util.List;

public class AppendMode implements IMode{
    private static AppendMode appendMode;
    private AppendMode(){}
    public static AppendMode getInstance() {
        if (appendMode == null) appendMode = new AppendMode();
        return appendMode;
    }
    @Override
    public int doFunction(NormalUser user) {
        List<NormalUser> users = FileParser.parseUsersFile("LoggedUsers", null);
        users.add(user);
        FileWriter.writeTo(users, "LoggedUsers", new CustomUserSerializer());
        return user.getID();
    }
}
