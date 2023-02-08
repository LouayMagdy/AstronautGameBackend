package com.example.astronautgamebackend.JsonParserWriter.GameSerializer;

import com.example.astronautgamebackend.GameService.gameLoop.Game;
import com.example.astronautgamebackend.GameService.Movables.IMovable;
import com.example.astronautgamebackend.GameService.Movables.Movable;
import com.google.gson.*;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class CustomGameSerializerDuringGame implements JsonSerializer<Game> {
    private static final GsonBuilder gsonBuilder = new GsonBuilder();
    @Override
    public JsonElement serialize(Game game, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject jsonGame = new JsonObject();
        List<IMovable> movables = new ArrayList<>(game.getMovables());
        List<Integer> emptyIndices = game.getEmptyIndices();
        for (int i = 0; i < emptyIndices.size(); i++)
            if(emptyIndices.get(i) < movables.size()) movables.remove((int) emptyIndices.get(i));
        JsonArray jsonMovables = new JsonArray();
        gsonBuilder.registerTypeAdapter(Movable.class, new MovableSerializer());
        for (IMovable movable: movables) jsonMovables.add(gsonBuilder.create().toJson(movable));
        jsonGame.addProperty("life", game.getAstronaut().getLife());
        jsonGame.add("movables", jsonMovables.getAsJsonArray());
        return jsonGame.getAsJsonObject();
    }
}
