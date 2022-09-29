package com.example.astronautgamebackend.JsonFilesReaderWriter;

import com.example.astronautgamebackend.UserAndRegisteration.model.NormalUser;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;

import java.io.FileReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

public class FileParser {
    public static List<NormalUser> parseUsersFile(String fileName){
        List<NormalUser> list = new ArrayList<>();
        try {
            Reader reader = new FileReader(System.getProperty("user.dir") + "\\users\\" + fileName + ".json");
            Object read = JsonParser.parseReader(reader);

            JsonArray jsonArray = (JsonArray) read;
            for (int i = 0; i < jsonArray.size(); i++)
                list.add(new Gson().fromJson(jsonArray.get(i), NormalUser.class));
            reader.close();
        }
        catch (Exception e){
            System.out.println("no users yet");
        }
        return list;
    }
}
