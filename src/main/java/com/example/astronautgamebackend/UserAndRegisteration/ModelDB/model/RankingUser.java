package com.example.astronautgamebackend.UserAndRegisteration.ModelDB.model;


import lombok.*;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;

@Setter
@Getter
@Entity
@Slf4j
@NoArgsConstructor
public class RankingUser {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private NormalUser user;

    @Basic(optional = false, fetch = FetchType.LAZY)
    private int avgLife;

    @Basic(optional = false, fetch = FetchType.LAZY)
    private int avgCollectedFood;

    @Basic(optional = false, fetch = FetchType.LAZY)
    private int gamesPlayedNum;

    public RankingUser(NormalUser user, int avgLife, int avgCollectedFood, int gamesPlayedNum) {
        this.user = user;
        this.avgLife = avgLife;
        this.avgCollectedFood = avgCollectedFood;
        this.gamesPlayedNum = gamesPlayedNum;
    }
}
