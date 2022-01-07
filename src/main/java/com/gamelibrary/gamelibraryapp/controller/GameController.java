package com.gamelibrary.gamelibraryapp.controller;

import com.gamelibrary.gamelibraryapp.exception.InformationExistException;
import com.gamelibrary.gamelibraryapp.exception.InformationNotFoundException;
import com.gamelibrary.gamelibraryapp.model.Developer;
import com.gamelibrary.gamelibraryapp.model.Game;
import com.gamelibrary.gamelibraryapp.model.Genre;
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
    public void setGameService(GameService gameService) {
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
        return gameService.updateGame(gameId, gameObject);

    }

    //Delete a single game
    @DeleteMapping(path = "/game/{gameId}")
    public Optional<Game> deleteGame(@PathVariable Long gameId) {
        LOGGER.info("calling deleteGame method from controller");
        return gameService.deleteGame(gameId);

    }


    @GetMapping(path = "/game/{gameId}/genre/")
    public List<Genre> getGenres(@PathVariable Long gameId) {
        LOGGER.info("Calling getGenres method from controller");
        return gameService.getGenres();
    }

    //Calls the service class to get all genres in the model
    @GetMapping(path = "/game/{gameId}/genre/{genreId}")
    public Optional getGenre(@PathVariable Long genreId, @PathVariable Long gameId) {
        LOGGER.info("Calling getGenre method from controller");
        return gameService.getGenre(genreId);
    }

    //calls the service class to get a single genre in the model using genre id.
    @GetMapping(path = "/game/{gameId}/genre/{genreId}/")
    public Optional<Game> getGamesInGenre(@PathVariable Long genreId, @PathVariable Long gameId) {
        LOGGER.info("Calling getGamesInGenre method from controller");
        return gameService.getGamesInGenre(genreId);
    }

    //Calls the service class to get all the games in a single genre using the genre id.
    @PostMapping(path = "/game/{gameId}/genre")
    public Genre createGenre(@PathVariable Genre genreObject, @PathVariable Long gameId) {
        LOGGER.info("Calling createGenre method from controller");
        return gameService.createGenre(genreObject);
    }

    //Calls the service class to allow the game to create a new genre.
    @PutMapping(path = "/game/{gameId}/genre/{genreId}")
    public Genre updateGenre(@PathVariable(value = "genreId") Long genreId, @RequestBody Genre genreObject, @PathVariable Long gameId) {
        LOGGER.info("Calling updateGenre method from controller");
        return gameService.updateGenre(genreId, genreObject);
    }

    //Calls the service class to allow a gamer to update a genre by the genre id.
    @DeleteMapping(path = "/game/{gameId}/genre/{genreId}")
    public Optional<Genre> deleteGenre(@PathVariable Long genreId, @PathVariable Long gameId) {
        LOGGER.info("Calling deleteGenre method from controller");
        return gameService.deleteGenre(genreId);

    }

    //Get all developers
    @GetMapping(path = "/game/{gameId}/genre/{genreId}/developer")
    public List<Developer> getDevelopers(@PathVariable String gameId, @PathVariable Long genreId) {
        LOGGER.info("calling getDevelopers method from controller");
        return gameService.getDevelopers();

    }

    //Get a Single developer
    @GetMapping(path = "/game/{gameId}/genre/{genreId}/developer/{developerId}")
    public Optional<Developer> getDeveloper(@PathVariable(value = "developerId") Long developerId, @PathVariable(value = "gameId") Long gameId, @PathVariable(value = "genreId") Long genreId) {
        LOGGER.info("calling getDeveloper method from controller");
        return gameService.getDeveloper(developerId);
    }

    //Get all games from a developer
    @GetMapping(path = "/game/{gameId}/genre/{genreId}/developer/{developerId}/")
    public List<Game> getDeveloperGames(@PathVariable(value = "developerId") Long developerId, @PathVariable(value = "gameId") Long gameId, @PathVariable(value = "genreId") Long genreId) {
        LOGGER.info("calling getDeveloperGames method from controller");
        return gameService.getDeveloperGames(developerId);
    }

    //Get single game from a developer
    @GetMapping(path = "/game/{gameId}/genre/{genreId}/developer/{developerId}/")
    public Game getDeveloperGame(@PathVariable(value = "developerId") Long developerId, @PathVariable(value = "gameId") Long gameId, @PathVariable(value = "genreId") Long genreId) {
        LOGGER.info("calling getDeveloperGame method from controller");
        return gameService.getDeveloperGame(developerId, gameId);
    }


    //Create a developer
    @PostMapping(path = "/game/{gameId}/genre/{genreId}/developer/")
    public Developer createDeveloper(@RequestBody Developer developerObject, @PathVariable Long gameId, @PathVariable Long genreId) {
        LOGGER.info("calling createDeveloper method from controller");
        return gameService.createDeveloper(developerObject);
    }

    //Update a single developer
    @PutMapping(path = "/game/{gameId}/genre/{genreId}/developer/{developerId}")
    public Developer updateDeveloper(@PathVariable(value = "developerId") Long developerId, @RequestBody Developer developerObject, @PathVariable(value = "gameId") Long gameId, @PathVariable(value = "genreId") Long genreId) {
        LOGGER.info("calling updateDeveloper method from controller");
        return gameService.updateDeveloper(developerId, developerObject);

    }

    //delete a single developer
    @DeleteMapping(path = "/game/{gameId}/genre/{genreId}/developer/{developerId}")
    public Optional<Developer> deleteDeveloper(@PathVariable(value = "developerId") Long developerId, @PathVariable(value = "gameId") Long gameId, @PathVariable(value = "genreId") Long genreId) {
        LOGGER.info("calling deleteDeveloper method from controller");
        return gameService.deleteDeveloper(developerId);

    }
}
