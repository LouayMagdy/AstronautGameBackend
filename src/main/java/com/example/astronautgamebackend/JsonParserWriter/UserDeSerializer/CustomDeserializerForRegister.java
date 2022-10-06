package com.example.astronautgamebackend.JsonParserWriter.UserDeSerializer;

import com.example.astronautgamebackend.UserAndRegisteration.model.NormalUser;
import com.google.gson.*;

import java.lang.reflect.Type;

public class CustomDeserializerForRegister implements JsonDeserializer<NormalUser> {
    @Override
    public NormalUser deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        JsonObject userJson = jsonElement.getAsJsonObject();
        return new NormalUser(userJson.get("userName").getAsString(), userJson.get("password").getAsString());
    }
}
