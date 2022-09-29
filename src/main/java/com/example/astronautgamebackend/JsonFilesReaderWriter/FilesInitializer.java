package com.example.astronautgamebackend.JsonFilesReaderWriter;

import java.io.File;
import java.io.IOException;

public class FilesInitializer {
    public static void createFiles(){
        File usersDirectory = new File(System.getProperty("user.dir") + "\\users");
        boolean res = usersDirectory.mkdir();
        if(res) System.out.println("the directory created successfully");
        else System.out.println("the directory already exist");
        try{
            File allUsersFile = new File(System.getProperty("user.dir") + "\\users" + "\\AllUsers.json");
            res = res && allUsersFile.createNewFile();
            File loggedUsersFile = new File(System.getProperty("user.dir") + "\\users" + "\\LoggedUsers.json");
            res = res && loggedUsersFile.createNewFile();
            File rankingUsersFile = new File(System.getProperty("user.dir") + "\\users" + "\\RankingUsers.json");
            res = res && rankingUsersFile.createNewFile();
            if(res) System.out.println("3 files created successfully");
            else System.out.println("3 files already exist");
        }
        catch (IOException e){
            System.out.println("error occurred on files creation");
        }
    }
}
