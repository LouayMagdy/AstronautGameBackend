package com.example.astronautgamebackend.Controller.RegiterationController;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Response<T>{
    private T message;
}
