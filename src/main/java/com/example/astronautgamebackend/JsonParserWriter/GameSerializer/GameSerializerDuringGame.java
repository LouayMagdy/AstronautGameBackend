package com.example.astronautgamebackend.JsonParserWriter.GameSerializer;

import com.example.astronautgamebackend.GameService.Game;
import com.example.astronautgamebackend.GameService.IGame;
import com.google.gson.GsonBuilder;

import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

public class GameSerializerDuringGame implements Encoder.Text<IGame> {
    private static GsonBuilder gsonBuilder = new GsonBuilder();
    @Override
    public String encode(IGame game) throws EncodeException {
        gsonBuilder.registerTypeAdapter(Game.class, new CustomGameSerializerDuringGame());
        String jsonGame = gsonBuilder.setPrettyPrinting().create().toJson(game);
        System.out.println(jsonGame);
        return jsonGame;
    }

    @Override
    public void init(EndpointConfig endpointConfig) {

    }

    @Override
    public void destroy() {

    }
}
