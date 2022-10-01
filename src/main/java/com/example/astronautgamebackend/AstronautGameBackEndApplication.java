package com.example.astronautgamebackend;

import com.example.astronautgamebackend.Controller.GameController.MatchController;
import com.example.astronautgamebackend.JsonFilesReaderWriter.FilesInitializer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AstronautGameBackEndApplication {

    public static void main(String[] args) {
        FilesInitializer.createFiles();
        SpringApplication.run(AstronautGameBackEndApplication.class, args);
    }

}
