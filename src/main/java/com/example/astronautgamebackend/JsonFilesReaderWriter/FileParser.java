package com.example.astronautgamebackend.JsonFilesReaderWriter;

import com.example.astronautgamebackend.UserAndRegisteration.model.NormalUser;
import com.google.gson.*;

import java.io.FileReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

public class FileParser {
    private static GsonBuilder gsonBuilder = new GsonBuilder();
    public static List<NormalUser> parseUsersFile(String fileName, JsonDeserializer<NormalUser> customizer){
        if (customizer != null) gsonBuilder.registerTypeAdapter(NormalUser.class, customizer);
        List<NormalUser> list = new ArrayList<>();
        try {
            Reader reader = new FileReader(System.getProperty("user.dir") + "\\users\\" + fileName + ".json");
            Object read = JsonParser.parseReader(reader);

            JsonArray jsonArray = (JsonArray) read;
            for (int i = 0; i < jsonArray.size(); i++)
                list.add(gsonBuilder.create().fromJson(jsonArray.get(i), NormalUser.class));
            reader.close();
        }
        catch (Exception e){
            System.out.println("no users yet");
        }
        return list;
    }
}
