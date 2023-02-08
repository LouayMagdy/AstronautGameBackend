package com.example.astronautgamebackend.UserAndRegisteration.ModelDB.model;


import lombok.*;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;

@Setter
@Getter
@Entity
@Table(name = "RankingUser", indexes = {
        @Index(name = "idx_rankinguser", columnList = "avgCollectedFood"),
        @Index(name = "idx_rankinguser_avglife", columnList = "avgLife")
})
@Slf4j
@NoArgsConstructor
public class RankingUser {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_name", referencedColumnName = "userName", unique = true)
    private NormalUser user;

    @Basic(optional = false, fetch = FetchType.EAGER)
    private int avgLife;

    @Basic(optional = false, fetch = FetchType.EAGER)
    private int avgCollectedFood;

    @Basic(optional = false, fetch = FetchType.EAGER)
    private int gamesPlayedNum;

    public RankingUser(NormalUser user, int avgLife, int avgCollectedFood, int gamesPlayedNum) {
        this.user = user;
        this.avgLife = avgLife;
        this.avgCollectedFood = avgCollectedFood;
        this.gamesPlayedNum = gamesPlayedNum;
    }
}
