package com.gamelibrary.gamelibraryapp.controller;

import com.gamelibrary.gamelibraryapp.exception.InformationExistException;
import com.gamelibrary.gamelibraryapp.exception.InformationNotFoundException;
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
}
