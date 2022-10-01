package com.example.astronautgamebackend.UserAndRegisteration.RegisterationService.Append;

import com.example.astronautgamebackend.GameService.RankingService.Score;
import com.example.astronautgamebackend.JsonFilesReaderWriter.FileParser;
import com.example.astronautgamebackend.JsonFilesReaderWriter.FileWriter;
import com.example.astronautgamebackend.JsonParserWriter.userSerializer.CustomRankingSerializer;
import com.example.astronautgamebackend.UserAndRegisteration.model.NormalUser;

import java.util.List;

public class AppendIIMode extends Wrapper{

    private static AppendIIMode appendMode;
    private AppendIIMode(IMode wrapped) {
        super(wrapped);
    }
    public static AppendIIMode getInstance(IMode wrapped) {
        if (appendMode == null) appendMode = new AppendIIMode(wrapped);
        return appendMode;
    }
    @Override
    public int doFunction(NormalUser user){
        List<NormalUser> users = FileParser.parseUsersFile("AllUsers", null);
        user.setID(users.size() + 1);
        users.add(user);
        FileWriter.writeTo(users, "AllUsers", null);
        List<NormalUser> rankings = FileParser.parseUsersFile("RankingUsers", null);
        user.setScore(new Score(0, 0, 0));
        rankings.add(user);
        FileWriter.writeTo(rankings, "RankingUsers", new CustomRankingSerializer());
        super.doFunction(user);
        return user.getID();
    }
}
