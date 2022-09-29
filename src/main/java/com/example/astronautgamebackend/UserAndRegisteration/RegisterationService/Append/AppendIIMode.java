package com.example.astronautgamebackend.UserAndRegisteration.RegisterationService.Append;

import com.example.astronautgamebackend.JsonFilesReaderWriter.FileParser;
import com.example.astronautgamebackend.JsonFilesReaderWriter.FileWriter;
import com.example.astronautgamebackend.UserAndRegisteration.model.NormalUser;

import java.util.List;

public class AppendIIMode extends Wrapper{
    public AppendIIMode(IMode wrapped) {
        super(wrapped);
    }

    @Override
    public int doFunction(NormalUser user){
        List<NormalUser> users = FileParser.parseUsersFile("AllUsers");
        user.setID(users.size() + 1);
        users.add(user);
        FileWriter.writeTo(users, "AllUsers");
        super.doFunction(user);
        return user.getID();
    }
}
