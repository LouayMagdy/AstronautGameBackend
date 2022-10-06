package com.example.astronautgamebackend.UserAndRegisteration.model;

import com.example.astronautgamebackend.GameService.IGame;
import com.example.astronautgamebackend.GameService.RankingService.Score;
import com.example.astronautgamebackend.JsonFilesReaderWriter.FileParser;

import java.util.List;

public class NormalUser{
    private String fullName;
    private String userName;
    private String password;
    private boolean gender; /// true: male, false: female
    private int iD;

    private Score score;




    public NormalUser(String fullName, String userName, String password, boolean gender) {
        this.fullName = fullName;
        this.userName = userName;
        this.password = password;
        this.gender = gender;
//        this.score = new Score(0, 0, 0);
    }
    public NormalUser(String fullName, String userName, String password, boolean gender, Score score) {
        this.fullName = fullName;
        this.userName = userName;
        this.password = password;
        this.gender = gender;
        this.score = score;
    }

    public NormalUser(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public NormalUser(String userName, int iD, Score score) {
        this.userName = userName;
        this.iD = iD;
        this.score = score;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NormalUser that = (NormalUser) o;
        return userName.equals(that.userName) && password.equals(that.password);
    }
    public NormalUser(int iD) {
        List<NormalUser> list = FileParser.parseUsersFile("AllUsers", null);
        this.fullName = list.get(iD - 1).getFullName();
        this.userName = list.get(iD - 1).getUserName();
        this.password = list.get(iD - 1).getPassword();
        this.gender = list.get(iD - 1).isMale();
        this.iD = iD;
    }
    public String getFullName() {return fullName;}
    public String getUserName() {return userName;}
    public String getPassword() {return password;}
    public boolean isMale() {return gender;}
    public int getID() {return iD;}
    public void setID(int iD){this.iD = iD;}

    @Override
    public String toString() {
        return "NormalUser{" +
                "fullName='" + fullName + '\'' +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", gender=" + gender +
                ", iD=" + iD +
                ", score=" + score.getAvgCollectedFood() + " " + score.getAvgLife() + " " + score.getGamePlayed() +
                '}';
    }

    public Score getScore() {return score;}

    public void setScore(Score score) {this.score = score;}
}
