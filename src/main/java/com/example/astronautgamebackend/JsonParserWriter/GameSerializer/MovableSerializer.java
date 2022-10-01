package com.example.astronautgamebackend.JsonParserWriter.GameSerializer;

import com.example.astronautgamebackend.GameService.Movables.Movable;
import com.google.gson.*;

import java.lang.reflect.Type;

public class MovableSerializer implements JsonSerializer<Movable> {

    @Override
    public JsonElement serialize(Movable movable, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject movableJson = new JsonObject();
        JsonObject point = new JsonObject();
        point.addProperty("x", movable.getPosition().getX());
        point.addProperty("y", movable.getPosition().getY());
        movableJson.addProperty("type", movable.getType());
        movableJson.add("point", point.getAsJsonObject());
        return movableJson.getAsJsonObject();
    }
}
