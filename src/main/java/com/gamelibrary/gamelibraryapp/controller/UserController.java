package com.gamelibrary.gamelibraryapp.controller;

import com.gamelibrary.gamelibraryapp.model.Request.LoginRequest;
import com.gamelibrary.gamelibraryapp.model.User;
import com.gamelibrary.gamelibraryapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Logger;

@RestController
@RequestMapping(path = "/auth/users")
public class UserController {
    private UserService userService;
    private static final Logger LOGGER = Logger.getLogger(UserController.class.getName());



    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public User createUser(@RequestBody User userObject) {
        LOGGER.info("calling createUser method from controller");
        return userService.createUser(userObject);
    }


    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginRequest loginRequest){
        return userService.loginUser(loginRequest);
    }
}