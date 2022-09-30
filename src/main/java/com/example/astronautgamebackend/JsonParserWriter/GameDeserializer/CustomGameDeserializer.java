package com.example.astronautgamebackend.JsonParserWriter.GameDeserializer;

import com.example.astronautgamebackend.GameService.Astronaut.Astronaut;
import com.example.astronautgamebackend.GameService.Game;
import com.example.astronautgamebackend.GameService.GeometricShapes.Circle;
import com.google.gson.*;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class CustomGameDeserializer implements JsonDeserializer<Game> {
    private static final Gson gson = new Gson();
    @Override
    public Game deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        JsonObject astronautJSon = jsonElement.getAsJsonObject();
        JsonArray circlesJSon = (JsonArray) astronautJSon.get("circles");
        List<Circle> circles = new ArrayList<>();
        for (int i = 0; i < circlesJSon.size(); i++) circles.add(gson.fromJson(circlesJSon.get(i), Circle.class));
        return new Game(new Astronaut(circles));
    }
}
