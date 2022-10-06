package com.example.astronautgamebackend.JsonParserWriter.UserDeSerializer;

import com.example.astronautgamebackend.GameService.RankingService.Score;
import com.example.astronautgamebackend.UserAndRegisteration.model.NormalUser;
import com.google.gson.*;

import java.lang.reflect.Type;

public class RankingDeserializer implements JsonDeserializer<NormalUser> {
    @Override
    public NormalUser deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        JsonObject userJson = jsonElement.getAsJsonObject();
        System.out.println("==>" + userJson.get("score").getAsJsonObject().get("gamesPlayed"));
        NormalUser user = new NormalUser(userJson.get("userName").getAsString()
                , userJson.get("iD").getAsInt(), new Gson().fromJson(userJson.get("score"), Score.class));
        System.out.println(user.toString());
        return user;
    }
}
