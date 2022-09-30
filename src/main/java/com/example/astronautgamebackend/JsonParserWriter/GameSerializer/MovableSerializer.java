package com.example.astronautgamebackend.JsonParserWriter.GameSerializer;

import com.example.astronautgamebackend.GameService.Movables.Movable;
import com.google.gson.*;

import java.lang.reflect.Type;

public class MovableSerializer implements JsonSerializer<Movable> {

    @Override
    public JsonElement serialize(Movable movable, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject movableJson = new JsonObject();
        JsonObject point = new JsonObject();
        point.add("x", new JsonPrimitive(movable.getPosition().getY()));
        point.add("y", new JsonPrimitive(movable.getPosition().getY()));
        movableJson.add("point", point);
        movableJson.add("type", new JsonPrimitive(movable.getType()));
        return movableJson;
    }
}
