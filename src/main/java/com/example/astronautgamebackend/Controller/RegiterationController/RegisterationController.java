package com.example.astronautgamebackend.Controller.RegiterationController;

import com.example.astronautgamebackend.JsonParserWriter.UserDeSerializer.SignInParser;
import com.example.astronautgamebackend.JsonParserWriter.UserDeSerializer.signUpParser;
import com.example.astronautgamebackend.UserAndRegisteration.RegisterationService.IRegisterer;
import com.example.astronautgamebackend.UserAndRegisteration.RegisterationService.Registerer;
import com.example.astronautgamebackend.UserAndRegisteration.model.NormalUser;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/AstronautGame/Registration")
public class RegisterationController {
    IRegisterer registerer;
    public RegisterationController() {
        this.registerer = Registerer.getInstance();
        System.out.println("registration controller created");
    }

    @PostMapping("/SignUp")
    public int signUp(@RequestBody String user){
        return registerer.signUp(signUpParser.parseUser(user));
    }

    @PostMapping("/SignIn")
    public int signIn(@RequestBody String user){
        return registerer.signIn(SignInParser.parse(user));
    }

    @DeleteMapping("/LogOut/{userID}")
    public void logOut(@PathVariable int userID){
        registerer.logOut(new NormalUser(userID));
    }

    @GetMapping("/isLogged/{userID}")
    public boolean isLogged(@PathVariable int userID){return registerer.isLoggedIn(new NormalUser(userID));}
}
