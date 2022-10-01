package com.example.astronautgamebackend.JsonParserWriter.UserDeSerializer;

import com.example.astronautgamebackend.JsonParserWriter.GameDeserializer.CustomGameDeserializer;
import com.example.astronautgamebackend.UserAndRegisteration.model.NormalUser;
import com.google.gson.GsonBuilder;

public class SignInParser {
    private static GsonBuilder gsonBuilder = new GsonBuilder();
    public static NormalUser parse(String user){
        gsonBuilder.registerTypeAdapter(NormalUser.class, new CustomDeserializerForRegister());
        NormalUser normalUser = gsonBuilder.create().fromJson(user, NormalUser.class);
        System.out.println(normalUser.toString());
        return normalUser;
    }
}
