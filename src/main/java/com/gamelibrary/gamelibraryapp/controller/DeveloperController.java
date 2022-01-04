package com.gamelibrary.gamelibraryapp.controller;

import com.gamelibrary.gamelibraryapp.model.Developer;
import com.gamelibrary.gamelibraryapp.model.Game;
import com.gamelibrary.gamelibraryapp.service.DeveloperService;
import com.gamelibrary.gamelibraryapp.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@RestController
@RequestMapping(path = "/api")
public class DeveloperController {

    private static final Logger LOGGER = Logger.getLogger(GameController.class.getName());

    private DeveloperService developerService;

    @Autowired
    public void setDeveloperService(DeveloperService developerService){
        this.developerService = developerService;
    }



    @GetMapping(path = "/developer/")
    public List<Developer> getDevelopers() {
        LOGGER.info("calling getDevelopers method from controller");
        return developerService.getDevelopers();

    }

    @GetMapping(path = "/developer/{developerId}")
    public Optional<Developer> getDeveloper(@PathVariable Long developerId) {
        LOGGER.info("calling getDeveloper method from controller");
        return developerService.getDeveloper(developerId);
    }

    @PostMapping(path = "/developer/")
    public Developer createDeveloper(@RequestBody Developer developerObject) {
        LOGGER.info("calling createDeveloper method from controller");
        return developerService.createDeveloper(developerObject);
    }



}
