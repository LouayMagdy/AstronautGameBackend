package com.example.astronautgamebackend.JsonParserWriter.GameDeserializer;

import com.example.astronautgamebackend.GameService.gameLoop.Game;
import com.google.gson.GsonBuilder;

import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;

public class GameDeserializer implements Decoder.Text<Game> {
    private static GsonBuilder gsonBuilder = new GsonBuilder();
    @Override
    public Game decode(String s) throws DecodeException {
        gsonBuilder.registerTypeAdapter(Game.class, new CustomGameDeserializer());
        return gsonBuilder.create().fromJson(s, Game.class);
    }

    @Override
    public boolean willDecode(String s) {
        return s != null && !s.equals("");
    }

    @Override
    public void init(EndpointConfig endpointConfig) {

    }

    @Override
    public void destroy() {

    }
}
