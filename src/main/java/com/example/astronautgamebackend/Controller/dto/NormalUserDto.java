package com.example.astronautgamebackend.Controller.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * A DTO for the {@link com.example.astronautgamebackend.UserAndRegisteration.ModelDB.model.NormalUser} entity
 */
@Data
public class NormalUserDto implements Serializable {
    private final String fullName;
    private final String userName;
    private final String password;
    private final boolean gender;
}