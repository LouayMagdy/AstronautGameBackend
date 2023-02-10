package com.example.astronautgamebackend.Controller.RegiterationController;

import com.example.astronautgamebackend.Controller.dto.LoggingUser;
import com.example.astronautgamebackend.Controller.dto.NormalUserDto;
import com.example.astronautgamebackend.Controller.Response.RegistrationResponse;
import com.example.astronautgamebackend.Controller.config.JWTService;
import com.example.astronautgamebackend.UserAndRegisteration.RegistrationServiceDB.IUserRegisterer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("api/v1/auth/astronaut-game")
public class RegistrationController {
    @Autowired
    private IUserRegisterer userRegisterer;

    @Autowired
    private JWTService jwtService;
    @PostMapping("/sign-up")
    public ResponseEntity<RegistrationResponse> signUp(@RequestBody NormalUserDto user){
        boolean success = userRegisterer.addUser(user);
        return success? ResponseEntity.status(HttpStatus.CREATED).body(new RegistrationResponse(jwtService.generateToken(user.getUserName()))) :
                ResponseEntity.status(HttpStatus.FORBIDDEN).body(new RegistrationResponse("No Token cuz The User Already Registered"));
    }
    @PostMapping("/sign-in")
    public ResponseEntity<RegistrationResponse> signIn(@RequestBody LoggingUser user){
        String jwt = userRegisterer.authenticate(user);
        return (jwt != null)? ResponseEntity.status(HttpStatus.ACCEPTED).body(new RegistrationResponse(jwt)) :
        ResponseEntity.status(HttpStatus.FORBIDDEN).body(new RegistrationResponse("No Token cuz The User Not Registered"));
    }

}
