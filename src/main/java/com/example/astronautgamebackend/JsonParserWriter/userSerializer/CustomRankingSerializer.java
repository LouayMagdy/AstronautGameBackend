package com.example.astronautgamebackend.JsonParserWriter.userSerializer;

import com.example.astronautgamebackend.UserAndRegisteration.model.NormalUser;
import com.google.gson.*;

import java.lang.reflect.Type;

public class CustomRankingSerializer implements JsonSerializer<NormalUser> {
    @Override
    public JsonElement serialize(NormalUser normalUser, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject userJson = new JsonObject();
        userJson.addProperty("userName", normalUser.getUserName());
        userJson.addProperty("iD", normalUser.getID());
        JsonObject JsonScore = new JsonObject();
        JsonScore.addProperty("avgLife", normalUser.getScore().getAvgLife());
        JsonScore.addProperty("avgCollectedFood", normalUser.getScore().getAvgCollectedFood());
        JsonScore.addProperty("gamesPlayed", normalUser.getScore().getGamePlayed());
        userJson.add("score", JsonScore);
        System.out.println(userJson);
        return userJson;
    }
}
