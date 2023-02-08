package com.example.astronautgamebackend.Controller.GameController;

import com.example.astronautgamebackend.GameService.Astronaut.Astronaut;
import com.example.astronautgamebackend.GameService.Astronaut.IAstronaut;
import com.example.astronautgamebackend.GameService.gameLoop.Game;
import com.example.astronautgamebackend.GameService.GeometricShapes.Circle;
import com.example.astronautgamebackend.GameService.gameLoop.IGame;
import com.example.astronautgamebackend.GameService.RankingService.RankingEvaluator;
import com.example.astronautgamebackend.JsonParserWriter.GameDeserializer.GameDeserializer;
import com.example.astronautgamebackend.JsonParserWriter.GameSerializer.GameSerializerDuringGame;
import org.springframework.beans.factory.annotation.Autowired;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ServerEndpoint(value = "/astronaut-game/match", encoders = GameSerializerDuringGame.class, decoders = GameDeserializer.class)
public class MatchController {
    private final Map<String, IGame> runningGames;

    @Autowired
    private RankingEvaluator rankingEvaluator;


    public MatchController() {
        System.out.println("websocket endpoint created");
        this.runningGames = new HashMap<>();
    }
    @OnOpen
    public void onOpen(Session session) throws IOException, EncodeException {
        // Get session and WebSocket connection
        System.out.println("someone has started a match with id: " + session.getId());
        List<Circle> circles = new ArrayList<>();
        IGame game = new Game(100, 100, new Astronaut(circles), null);
        this.runningGames.put(session.getId(), game);
        game.play();
        session.getBasicRemote().sendObject(game);
    }

    @OnMessage
    public void onMessage(Session session, Game game) throws IOException, EncodeException {
        // Handle new messages
        System.out.println("a message received from :" + session.getId());
        IGame runningGame = this.runningGames.get(session.getId());
        IAstronaut astronaut = runningGame.getAstronaut();
        astronaut.setCircles(game.getAstronaut().getCircles());
        runningGame.setDimensions(game.getWidth(), game.getHeight());
        if(runningGame.getUserToken() == null) runningGame.setUserToken(game.getUserToken());
        if (!runningGame.isRunning()) onClose(session);
        session.getBasicRemote().sendObject(runningGame);
    }

    @OnClose
    public void onClose(Session session) throws IOException {
        IGame runningGame = runningGames.get(session.getId());
        if (runningGame == null) return;
        runningGame.terminateGame();
        runningGames.remove(session.getId());
        if (!runningGame.isRunning()) this.rankingEvaluator.saveGame(runningGame);
        session.getBasicRemote().sendText("Game Over");
    }

    @OnError
    public void onError(Throwable throwable) {
        throwable.printStackTrace();
    }

}
