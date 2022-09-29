package com.example.astronautgamebackend.JsonFilesReaderWriter;

import com.example.astronautgamebackend.UserAndRegisteration.model.NormalUser;
import com.google.gson.GsonBuilder;

import java.io.Writer;
import java.util.List;

public class FileWriter {
    public static void writeTo(List<NormalUser> list, String fileName){
        try{
            String jsonList = new GsonBuilder().setPrettyPrinting().create().toJson(list);
            Writer writer = new java.io.FileWriter(System.getProperty("user.dir") + "\\users\\" + fileName + ".json");
            writer.write(jsonList);
            writer.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
