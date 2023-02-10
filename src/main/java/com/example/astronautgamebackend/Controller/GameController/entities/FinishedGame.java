package com.example.astronautgamebackend.Controller.GameController.entities;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class FinishedGame {
    String token;
    int collectedFood;
    int life;
}
