package com.example.astronautgamebackend.Controller.RegiterationController;

import com.example.astronautgamebackend.JsonParserWriter.UserParser;
import com.example.astronautgamebackend.UserAndRegisteration.RegisterationService.IRegisterer;
import com.example.astronautgamebackend.UserAndRegisteration.RegisterationService.Registerer;
import com.example.astronautgamebackend.UserAndRegisteration.model.NormalUser;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/AstronautGame")
public class RegisterationController {
    IRegisterer registerer;
    public RegisterationController() {this.registerer = Registerer.getInstance();}

    @PostMapping("/SignUp")
    public int signUp(@RequestBody String user){
        return registerer.signUp(UserParser.parseUser(user));
    }

    @PostMapping("/SignIn")
    public int signIn(@RequestBody String user){
        return registerer.signIn(UserParser.parseUser(user));
    }

    @DeleteMapping("/LogOut/{userID}")
    public void logOut(@PathVariable int userID){
        registerer.logOut(new NormalUser(userID));
    }
}
