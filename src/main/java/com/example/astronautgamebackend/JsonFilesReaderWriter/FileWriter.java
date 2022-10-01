package com.example.astronautgamebackend.JsonFilesReaderWriter;

import com.example.astronautgamebackend.UserAndRegisteration.model.NormalUser;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSerializer;

import java.io.Writer;
import java.util.List;

public class FileWriter {
    private static GsonBuilder gsonBuilder = new GsonBuilder();
    public static void writeTo(List<NormalUser> list, String fileName, JsonSerializer<NormalUser> customSerializer){
        try{
            if (customSerializer != null) gsonBuilder.registerTypeAdapter(NormalUser.class, customSerializer);
            String jsonList = gsonBuilder.setPrettyPrinting().create().toJson(list);
            Writer writer = new java.io.FileWriter(System.getProperty("user.dir") + "\\users\\" + fileName + ".json");
            writer.write(jsonList);
            writer.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
