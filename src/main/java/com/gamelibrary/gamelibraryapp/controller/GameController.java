package com.gamelibrary.gamelibraryapp.controller;

import com.gamelibrary.gamelibraryapp.model.Developer;
import com.gamelibrary.gamelibraryapp.model.Game;
import com.gamelibrary.gamelibraryapp.model.Genre;
import com.gamelibrary.gamelibraryapp.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
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
       return Optional.ofNullable(gameService.getGame(gameId));
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
    public Game deleteGame(@PathVariable Long gameId) {
        LOGGER.info("calling deleteGame method from controller");
        return gameService.deleteGame(gameId);

    }

    @GetMapping(path = "/game/{gameId}/genre")
    public List<Genre> getGenres(@PathVariable(value = "gameId")Long gameId) {
        LOGGER.info("Calling getGenres method from controller");
        return gameService.getGenres(gameId);
    }
    //Calls the service class to get all genres in the model
    @GetMapping(path = "/game/{gameId}/genre/{genreId}")
    public Genre getGenre(@PathVariable(value = "gameId") Long gameId, @PathVariable(value = "genreId") Long genreId) {
        LOGGER.info("Calling getGenre method from controller");
        return gameService.getGenre(gameId, genreId);
    }

    //Calls the service class to get all the games in a single genre using the genre id.
    @PostMapping(path = "/game/{gameId}/genre")
    public Genre createGenre(@PathVariable(value = "gameId") Long gameId, @RequestBody Genre genreObject) {
        LOGGER.info("Calling createGenre method from controller");
        return gameService.createGenre(gameId, genreObject);
    }

    //Calls the service class to allow the game to create a new genre.
    @PutMapping(path = "/game/{gameId}/genre/{genreId}")
    public Genre updateGenre(@PathVariable(value = "gameId") Long gameId, @PathVariable(value = "genreId") Long genreId, @RequestBody Genre genreObject) {
        LOGGER.info("Calling updateGenre method from controller");
        return gameService.updateGenre(gameId, genreId, genreObject);
    }

    //Calls the service class to allow a gamer to update a genre by the genre id.
    @DeleteMapping(path = "/game/{gameId}/genre/{genreId}")
    public ResponseEntity<HashMap> deleteGenre(@PathVariable(value = "gameId") Long gameId, @PathVariable(value = "genreId") Long genreId) {
        LOGGER.info("Calling deleteGenre method from controller");
        gameService.deleteGenre(gameId,genreId);
        HashMap responseMessage = new HashMap();
        responseMessage.put("status", "genre with id: " + genreId + " was successfully deleted.");
        return new ResponseEntity<HashMap>(responseMessage, HttpStatus.OK);
    }


    //Get all developers
    @GetMapping(path = "/game/{gameId}/developer/")
    public List<Developer> getDevelopers(@PathVariable(value = "gameId") Long gameId) {
        LOGGER.info("calling getDevelopers method from controller");
        return gameService.getDevelopers(gameId);

    }

    //Get a Single developer
    @GetMapping(path = "/game/{gameId}/developer/{developerId}")
    public Developer getDeveloper(@PathVariable(value = "gameId")Long gameId, @PathVariable Long developerId) {
        LOGGER.info("calling getDeveloper method from controller");
        return gameService.getDeveloper(gameId,developerId);
    }

    //Get all games from a developer
//    @GetMapping(path = "/game/{gameId}/developer/{developerId}/")
//    public List<Game> getDeveloperGames(@PathVariable(value = "gameId")Long gameId, @PathVariable Long developerId) {
//        LOGGER.info("calling getDeveloperGames method from controller");
//        return gameService.getDeveloperGames(developerId);
//    }
//
//    //Get single game from a developer
//    @GetMapping(path = "/game/{gameId}/developer/{developerId}/")
//    public Game getDeveloperGame(@PathVariable(value = "gameId")Long gameId, @PathVariable Long developerId){
//        LOGGER.info("calling getDeveloperGame method from controller");
//        return gameService.getDeveloperGame(gameId,developerId);
//    }


    //Create a developer
    @PostMapping(path = "game/{gameId}/developer/")
    public Developer createDeveloper(@PathVariable(value = "gameId") Long gameId,@RequestBody Developer developerObject) {
        LOGGER.info("calling createDeveloper method from controller");
        return gameService.createDeveloper(gameId, developerObject);
    }

    //Update a single developer
    @PutMapping(path = "/game/{gameId}/developer/{developerId}/")
    public Developer updateDeveloper(@PathVariable(value = "gameId")Long gameId, @PathVariable Long developerId, @RequestBody Developer developerObject) {
        LOGGER.info("calling updateDeveloper method from controller");
        return gameService.updateDeveloper(gameId,developerId,developerObject);

    }

    //delete a single developer
    @DeleteMapping(path = "/developer/{developerId}")
    public ResponseEntity<HashMap> deleteDeveloper(@PathVariable(value = "gameId")Long gameId, @PathVariable(value = "developerId") Long developerId) {
        LOGGER.info("calling deleteDeveloper method from controller");
        gameService.deleteDeveloper(gameId,developerId);
        HashMap responseMessage = new HashMap();
        responseMessage.put("status", "developer with id: " + developerId + " was successfully deleted.");
        return new ResponseEntity<>(responseMessage, HttpStatus.OK);

    }






}
