package com.gamelibrary.gamelibraryapp.controller;

import com.gamelibrary.gamelibraryapp.exception.InformationNotFoundException;
import com.gamelibrary.gamelibraryapp.model.Game;
import com.gamelibrary.gamelibraryapp.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@RestController
@RequestMapping(path = "/api")
public class GameController {

    private static final Logger LOGGER = Logger.getLogger(GameController.class.getName());
    private GameRepository gameRepository;

    @Autowired
    public void setGameRepository(GameRepository gameRepository){
        this.gameRepository = gameRepository;
    }





    @GetMapping(path = "/game/")
    public List<Game> getGames(){
        LOGGER.info("calling getGames method from controller");
        return gameRepository.findAll();

    }

    @GetMapping(path = "/game/{gameId}")
    public Optional getGame(@PathVariable Long gameId){
        LOGGER.info("calling getGame method from controller");
        Optional<Game> game = gameRepository.findById(gameId);
        if(game.isPresent()){
            return game;
        }else{
            throw new InformationNotFoundException("game with id " + gameId + " is not found");
        }

    }

    @PostMapping(path = "/game/")
    public Game createGame(@RequestBody Game gameObject){
        LOGGER.info("calling createGame method from controller");

    }

    @PutMapping(path = "/game/{gameId}")
    public Game updateGame(@PathVariable(value = "gameId") Long gameId, @RequestBody Game gameObject){
        LOGGER.info("calling updateGame method from controller");

    }

    @DeleteMapping(path = "/game/{gameId}")
    public Optional<Game> deleteGame(@PathVariable Long gameId){
        LOGGER.info("calling deleteGame method from controller");

    }




}
