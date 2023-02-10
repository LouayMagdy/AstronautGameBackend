package com.example.astronautgamebackend.Controller.GameController;

import com.example.astronautgamebackend.Controller.GameController.entities.FinishedGame;
import com.example.astronautgamebackend.Controller.GameController.mappers.FinishedGameMapper;
import com.example.astronautgamebackend.GameService.Astronaut.Astronaut;
import com.example.astronautgamebackend.GameService.Astronaut.IAstronaut;
import com.example.astronautgamebackend.GameService.GeometricShapes.Circle;
import com.example.astronautgamebackend.GameService.gameLoop.Game;
import com.example.astronautgamebackend.GameService.gameLoop.IGame;
import com.example.astronautgamebackend.JsonParserWriter.GameDeserializer.GameDeserializer;
import com.example.astronautgamebackend.JsonParserWriter.GameSerializer.GameSerializerDuringGame;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.client.RestTemplate;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import org.springframework.http.HttpHeaders;

import java.util.*;

@ServerEndpoint(value = "/api/v1/auth/astronaut-game/match", encoders = GameSerializerDuringGame.class, decoders = GameDeserializer.class)
@Setter
@Getter
public class MatchWSController{
    private Map<String, IGame> runningGames = new HashMap<>();
    private final RestTemplate restTemplate = new RestTemplateBuilder().build();
    private final FinishedGameMapper finishedGameMapper = new FinishedGameMapper();
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
        if (!runningGame.isRunning()) saveGame(runningGame);
        session.getBasicRemote().sendText("Game Over");
    }
    @OnError
    public void onError(Throwable throwable) {
        throwable.printStackTrace();
    }

    public void saveGame(IGame game){
        FinishedGame finishedGame = this.finishedGameMapper.map(game);
        String url = "http://localhost:8080/api/v1/auth/astronaut-game/stats/save-game";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        HttpEntity<FinishedGame> entity = new HttpEntity<>(finishedGame, headers);
        this.restTemplate.postForObject(url, entity, String.class);
    }
}
