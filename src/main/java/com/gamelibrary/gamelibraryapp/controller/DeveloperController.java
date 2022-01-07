package com.gamelibrary.gamelibraryapp.controller;

import com.gamelibrary.gamelibraryapp.model.Developer;
import com.gamelibrary.gamelibraryapp.model.Game;
import com.gamelibrary.gamelibraryapp.service.DeveloperService;
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


    //Get all developers
    @GetMapping(path = "/developer/")
    public List<Developer> getDevelopers() {
        LOGGER.info("calling getDevelopers method from controller");
        return developerService.getDevelopers();

    }

    //Get a Single developer
    @GetMapping(path = "/developer/{developerId}")
    public Optional<Developer> getDeveloper(@PathVariable Long developerId) {
        LOGGER.info("calling getDeveloper method from controller");
        return Optional.ofNullable(developerService.getDeveloper(developerId));
    }

    //Get all games from a developer
    @GetMapping(path = "/developer/{developerId}/game")
    public List<Game> getDeveloperGames(@PathVariable Long developerId) {
        LOGGER.info("calling getDeveloperGames method from controller");
        return developerService.getDeveloperGames(developerId);
    }

    //Get single game from a developer
    @GetMapping(path = "/developer/{developerId}/game/{gameId}")
    public Game getDeveloperGame(@PathVariable Long developerId, @PathVariable Long gameId){
        LOGGER.info("calling getDeveloperGame method from controller");
        return developerService.getDeveloperGame(developerId, gameId);
    }


    //Create a developer
    @PostMapping(path = "/developer/")
    public Developer createDeveloper(@RequestBody Developer developerObject) {
        LOGGER.info("calling createDeveloper method from controller");
        return developerService.createDeveloper(developerObject);
    }

    //Update a single developer
    @PutMapping(path = "/developer/{developerId}")
    public Developer updateDeveloper(@PathVariable(value = "developerId") Long developerId, @RequestBody Developer developerObject) {
        LOGGER.info("calling updateDeveloper method from controller");
        return developerService.updateDeveloper(developerId,developerObject);

    }

    //delete a single developer
    @DeleteMapping(path = "/developer/{developerId}")
    public Optional<Developer> deleteDeveloper(@PathVariable Long developerId) {
        LOGGER.info("calling deleteDeveloper method from controller");
        return developerService.deleteDeveloper(developerId);

    }



}
