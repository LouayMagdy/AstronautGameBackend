package com.example.astronautgamebackend.JsonParserWriter.userSerializer;

import com.example.astronautgamebackend.UserAndRegisteration.model.NormalUser;
import com.google.gson.*;

import java.lang.reflect.Type;

public class CustomUserSerializer implements JsonSerializer<NormalUser> {
    @Override
    public JsonElement serialize(NormalUser normalUser, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject userJson = new JsonObject();
        userJson.addProperty("userName", normalUser.getUserName());
        userJson.addProperty("password", normalUser.getPassword());
        return userJson;
    }
}
