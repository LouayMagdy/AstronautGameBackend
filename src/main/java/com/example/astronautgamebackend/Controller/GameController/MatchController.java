package com.example.astronautgamebackend.Controller.GameController;

import com.example.astronautgamebackend.GameService.IGame;
import com.example.astronautgamebackend.JsonParserWriter.GameDeserializer.GameDeserializer;
import com.example.astronautgamebackend.JsonParserWriter.GameSerializer.GameSerializerDuringGame;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;

@ServerEndpoint(value = "/AstronautGame/Match",
        encoders = GameSerializerDuringGame.class, decoders = GameDeserializer.class)
public class MatchController {
    @OnOpen
    public void onOpen(Session session) throws IOException {
        // Get session and WebSocket connection
    }

    @OnMessage
    public void onMessage(Session session, IGame game) throws IOException {
        // Handle new messages
    }

    @OnClose
    public void onClose(Session session) throws IOException {
        // WebSocket connection closes
    }

    @OnError
    public void onError(Session session, Throwable throwable) {
        // Do error handling here
    }
}
