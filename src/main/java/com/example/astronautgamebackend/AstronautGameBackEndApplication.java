package com.example.astronautgamebackend;

import com.example.astronautgamebackend.GameService.Game;
import com.example.astronautgamebackend.JsonFilesReaderWriter.FilesInitializer;
import com.example.astronautgamebackend.JsonParserWriter.GameSerializer.GameSerializerDuringGame;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.websocket.EncodeException;

@SpringBootApplication
public class AstronautGameBackEndApplication {

    public static void main(String[] args) throws EncodeException, InterruptedException {
        FilesInitializer.createFiles();
        Game game = new Game(100, 100);
        game.play();
        Thread.sleep(60000);
        GameSerializerDuringGame serializer = new GameSerializerDuringGame();
        serializer.encode(game);
        SpringApplication.run(AstronautGameBackEndApplication.class, args);
    }

}
