package com.example.astronautgamebackend.UserAndRegisteration.ModelDB.model;

import lombok.*;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import java.io.Serializable;

@Slf4j
@Entity
@Table
@Setter
@Getter
@NoArgsConstructor
public class NormalUser {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int iD;

    @Basic(optional = false, fetch = FetchType.EAGER)
    @Column(length = 100)
    private String fullName;

    @Basic(optional = false, fetch = FetchType.EAGER)
    @Column(unique = true, length = 50)
    private String userName;

    @Basic(optional = false, fetch = FetchType.EAGER)
    @Column(length = 50)
    private String password;

    @Basic(optional = false, fetch = FetchType.EAGER)
    private boolean gender; /// true: male, false: female

    @OneToOne(mappedBy = "user")
    private RankingUser rankingUser;

    public NormalUser(@NonNull String fullName, @NonNull String userName, @NonNull String password, @NonNull boolean gender) {
        this.fullName = fullName;
        this.userName = userName;
        this.password = password;
        this.gender = gender;
    }
}
