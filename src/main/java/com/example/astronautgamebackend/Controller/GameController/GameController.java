package com.example.astronautgamebackend.Controller.GameController;

import com.example.astronautgamebackend.JsonFilesReaderWriter.FileParser;
import com.example.astronautgamebackend.JsonParserWriter.UserDeSerializer.RankingDeserializer;
import com.example.astronautgamebackend.JsonParserWriter.userSerializer.CustomRankingSerializer;
import com.example.astronautgamebackend.UserAndRegisteration.model.NormalUser;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/AstronautGame/Stats")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class GameController {
    @GetMapping("/Rankings")
    public String[] getRankings(){
        JsonArray jsonArray = new JsonArray();
        List<NormalUser> users = FileParser.parseUsersFile("RankingUsers", new RankingDeserializer());
        System.out.println(users.get(0).getScore().getGamePlayed());
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(NormalUser.class, new CustomRankingSerializer());
        Gson gson = gsonBuilder.create();
        for(int i = 0; i< users.size(); i++) jsonArray.add(gson.toJson(users.get(i)));
        String[] res = new String[jsonArray.size()];
        for(int i = 0; i< users.size(); i++) res[i] = jsonArray.get(i).getAsString();
        return res;
    }
}
