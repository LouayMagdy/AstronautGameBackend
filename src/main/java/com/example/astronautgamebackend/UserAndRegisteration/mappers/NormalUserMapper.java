package com.example.astronautgamebackend.UserAndRegisteration.mappers;

import com.example.astronautgamebackend.Controller.dto.NormalUserDto;
import com.example.astronautgamebackend.UserAndRegisteration.ModelDB.model.NormalUser;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
public class NormalUserMapper {
    private final PasswordEncoder passwordEncoder;

    public NormalUser map(NormalUserDto userDto){
        return new NormalUser(userDto.getFullName(), userDto.getUserName(),
                passwordEncoder.encode(userDto.getPassword()), userDto.isGender());
    }
}
