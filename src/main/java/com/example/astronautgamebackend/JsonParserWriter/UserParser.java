package com.example.astronautgamebackend.JsonParserWriter;

import com.example.astronautgamebackend.UserAndRegisteration.model.NormalUser;
import com.google.gson.Gson;

public class UserParser {

    public static NormalUser parseUser(String user){
        Gson gson = new Gson();
        return gson.fromJson(user, NormalUser.class);
    }
}
