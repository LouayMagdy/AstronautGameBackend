package com.example.astronautgamebackend.Controller.RegiterationController;

import com.example.astronautgamebackend.JsonParserWriter.UserDeSerializer.SignInParser;
import com.example.astronautgamebackend.JsonParserWriter.UserDeSerializer.signUpParser;
import com.example.astronautgamebackend.UserAndRegisteration.ModelDB.model.NormalUser;
import com.example.astronautgamebackend.UserAndRegisteration.RegisterationService.IRegisterer;
import com.example.astronautgamebackend.UserAndRegisteration.RegisterationService.Registerer;
import com.example.astronautgamebackend.UserAndRegisteration.RegistrationServiceDB.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/AstronautGame/Registration")
public class RegistrationController {

    @Autowired
    private IUserRegisterer userRegisterer;
    @PostMapping("/SignUp")
    public ResponseEntity<Response<String>> signUp(@RequestBody NormalUser user){
        boolean success = userRegisterer.addUser(user);
        return success? ResponseEntity.status(HttpStatus.CREATED).body(new Response<>("User Added Successfully")) :
                ResponseEntity.status(HttpStatus.FORBIDDEN).body(new Response<>("User Already Registered"));
    }

//    @PostMapping("/SignIn")
//    public int signIn(@RequestBody String user){
//        return registerer.signIn(SignInParser.parse(user));
//    }
//
//    @DeleteMapping("/LogOut/{userID}")
//    public void logOut(@PathVariable int userID){
//        registerer.logOut(new NormalUser(userID));
//    }

}
