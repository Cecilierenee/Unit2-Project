package com.gamelibrary.gamelibraryapp.controller;

import com.gamelibrary.gamelibraryapp.exception.InformationExistException;
import com.gamelibrary.gamelibraryapp.exception.InformationNotFoundException;
import com.gamelibrary.gamelibraryapp.model.Developer;
import com.gamelibrary.gamelibraryapp.model.Game;
import com.gamelibrary.gamelibraryapp.repository.GameRepository;
import com.gamelibrary.gamelibraryapp.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@RestController
@RequestMapping(path = "/api")
public class GameController {

    private static final Logger LOGGER = Logger.getLogger(GameController.class.getName());

    private GameService gameService;



    @Autowired
    public void setGameService(GameService gameService){
        this.gameService = gameService;
    }


    //Get all games
    @GetMapping(path = "/game/")
    public List<Game> getGames() {
        LOGGER.info("calling getGames method from controller");
        return gameService.getGames();

    }

    //Get a single game
    @GetMapping(path = "/game/{gameId}")
    public Optional<Game> getGame(@PathVariable Long gameId) {
        LOGGER.info("calling getGame method from controller");
       return gameService.getGame(gameId);
        }


    //Create a game
    @PostMapping(path = "/game/")
    public Game createGame(@RequestBody Game gameObject) {
        LOGGER.info("calling createGame method from controller");
        return gameService.createGame(gameObject);
    }

    //Update a single game
    @PutMapping(path = "/game/{gameId}")
    public Game updateGame(@PathVariable(value = "gameId") Long gameId, @RequestBody Game gameObject) {
        LOGGER.info("calling updateGame method from controller");
        return gameService.updateGame(gameId,gameObject);

    }

    //Delete a single game
    @DeleteMapping(path = "/game/{gameId}")
    public Optional<Game> deleteGame(@PathVariable Long gameId) {
        LOGGER.info("calling deleteGame method from controller");
        return gameService.deleteGame(gameId);

    }

    @GetMapping(path = "/game/developer/")
    public List<Developer> getDevelopers() {
        LOGGER.info("calling getDevelopers method from controller");
        return gameService.getDevelopers();

    }

    //Get a Single developer
    @GetMapping(path = "/game/developer/{developerId}")
    public Optional<Developer> getDeveloper(@PathVariable Long developerId) {
        LOGGER.info("calling getDeveloper method from controller");
        return gameService.getDeveloper(developerId);
    }

    //Get all games from a developer
    @GetMapping(path = "/game/developer/{developerId}/")
    public List<Game> getDeveloperGames(@PathVariable Long developerId) {
        LOGGER.info("calling getDeveloperGames method from controller");
        return gameService.getDeveloperGames(developerId);
    }

    //Get single game from a developer
    @GetMapping(path = "/game/{gameId}/developer/{developerId}/")
    public Game getDeveloperGame(@PathVariable Long developerId, @PathVariable Long gameId){
        LOGGER.info("calling getDeveloperGame method from controller");
        return gameService.getDeveloperGame(developerId, gameId);
    }


    //Create a developer
    @PostMapping(path = "/game/developer/")
    public Developer createDeveloper(@RequestBody Developer developerObject) {
        LOGGER.info("calling createDeveloper method from controller");
        return gameService.createDeveloper(developerObject);
    }

    //Update a single developer
    @PutMapping(path = "/game/developer/{developerId}")
    public Developer updateDeveloper(@PathVariable(value = "developerId") Long developerId, @RequestBody Developer developerObject) {
        LOGGER.info("calling updateDeveloper method from controller");
        return gameService.updateDeveloper(developerId,developerObject);

    }

    //delete a single developer
    @DeleteMapping(path = "/game/developer/{developerId}")
    public Optional<Developer> deleteDeveloper(@PathVariable Long developerId) {
        LOGGER.info("calling deleteDeveloper method from controller");
        return gameService.deleteDeveloper(developerId);

    }
}
