package com.example.astronautgamebackend.UserAndRegisteration.mappers;

import com.example.astronautgamebackend.Controller.RegiterationController.dto.NormalUserDto;
import com.example.astronautgamebackend.UserAndRegisteration.ModelDB.model.NormalUser;

public class NormalUserMapper {
    public static NormalUser map(NormalUserDto userDto){
        return new NormalUser(userDto.getFullName(), userDto.getUserName(), userDto.getPassword(), userDto.isGender());
    }
}
