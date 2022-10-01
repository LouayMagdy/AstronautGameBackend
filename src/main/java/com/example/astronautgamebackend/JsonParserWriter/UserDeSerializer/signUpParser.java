package com.example.astronautgamebackend.JsonParserWriter.UserDeSerializer;

import com.example.astronautgamebackend.UserAndRegisteration.model.NormalUser;
import com.google.gson.Gson;

public class signUpParser {

    public static NormalUser parseUser(String user){
        Gson gson = new Gson();
        NormalUser user1 = gson.fromJson(user, NormalUser.class);
        System.out.println(user1.toString());
        return user1;
    }
}
