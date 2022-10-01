package com.example.astronautgamebackend.Controller.GameController;

import com.example.astronautgamebackend.GameService.Astronaut.Astronaut;
import com.example.astronautgamebackend.GameService.Astronaut.IAstronaut;
import com.example.astronautgamebackend.GameService.Game;
import com.example.astronautgamebackend.GameService.GeometricShapes.Circle;
import com.example.astronautgamebackend.GameService.IGame;
import com.example.astronautgamebackend.GameService.RankingService.RankingEvaluator;
import com.example.astronautgamebackend.JsonParserWriter.GameDeserializer.GameDeserializer;
import com.example.astronautgamebackend.JsonParserWriter.GameSerializer.GameSerializerDuringGame;
import com.example.astronautgamebackend.UserAndRegisteration.model.IUser;
import com.example.astronautgamebackend.UserAndRegisteration.model.NormalUser;
import org.springframework.web.context.annotation.ApplicationScope;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ServerEndpoint(value = "/AstronautGame/Match", encoders = GameSerializerDuringGame.class, decoders = GameDeserializer.class)
public class MatchController {
    Map<String, IGame> games;

    public MatchController() {
        System.out.println("ws api created");
        this.games = new HashMap<>();
    }
    @OnOpen
    public void onOpen(Session session) throws IOException, EncodeException {
        // Get session and WebSocket connection
        System.out.println("someone has started a match");
        List<Circle> circles = new ArrayList<>();
        IGame game = new Game(100, 100, new Astronaut(circles), 0);////
        games.put(session.getId(), game);
        game.play();
        session.getBasicRemote().sendObject(game);
    }

    @OnMessage
    public void onMessage(Session session, Game game) throws IOException, EncodeException {
        // Handle new messages
        System.out.println("a message received from :" + session.getId());
        IGame ourGame = games.get(session.getId());
        IAstronaut astronaut = ourGame.getAstronaut();
        astronaut.setCircles(game.getAstronaut().getCircles());
        ourGame.setDimensions(game.getWidth(), game.getHeight());
        ourGame.setId(game.getUserID());
        if (!ourGame.isRunning()) session.getBasicRemote().sendText("Game Over");
        session.getBasicRemote().sendObject(ourGame);
    }

    @OnClose
    public void onClose(Session session) throws IOException {
        IGame ourGame = games.get(session.getId());
        games.remove(session.getId());
        if (!ourGame.isRunning()) RankingEvaluator.getInstance().saveGame(ourGame);
        session.getBasicRemote().sendText("Game Over");
    }

    @OnError
    public void onError(Throwable throwable) {
        throwable.printStackTrace();
    }
}
