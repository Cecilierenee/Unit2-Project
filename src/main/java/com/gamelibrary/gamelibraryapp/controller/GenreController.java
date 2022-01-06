package com.gamelibrary.gamelibraryapp.controller;


import com.gamelibrary.gamelibraryapp.exception.InformationExistException;
import com.gamelibrary.gamelibraryapp.exception.InformationNotFoundException;
import com.gamelibrary.gamelibraryapp.model.Game;
import com.gamelibrary.gamelibraryapp.model.Genre;
import com.gamelibrary.gamelibraryapp.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;



@RestController
@RequestMapping(path = "/api")
public class GenreController {
    private static final Logger LOGGER = Logger.getLogger(GenreController.class.getName());
    private GenreService genreService;

@Autowired
    public void setGenreService(GenreService genreService) {
        this.genreService = genreService;
    }


@GetMapping(path = "/genre")
public List<Genre> getGenres() {
    LOGGER.info("Calling getGenres method from controller");
    return genreService.getGenres();
}
//Calls the service class to get all genres in the model
@GetMapping(path = "/genre/{genreId}")
public Optional getGenre(@PathVariable Long genreId) {
    LOGGER.info("Calling getGenre method from controller");
    Optional<Genre> genre = genreService.getGenre(genreId);
    if (genre.isPresent()) {
        return genre;
    } else {
        throw new InformationNotFoundException("Genre with " +genreId + "Does not exist");
    }
}
//calls the service class to get a single genre in the model using genre id.
@GetMapping(path = "/genre/{genreId}/game")
public List<Game> getGamesInGenre(@PathVariable Long genreId) {
    LOGGER.info("Calling getGamesInGenre method from controller");
    List<Game> genre = genreService.getGamesInGenre(genreId);
    if (genre != null){
        return genreService.getGamesInGenre(genreId);
    } else {
        throw new InformationNotFoundException("Genre with " + genreId + " does not contain any games");
    }
}
//Calls the service class to get all the games in a single genre using the genre id.
@PostMapping(path = "/genre")
public Genre createGenre(@PathVariable Genre genreObject) {
    LOGGER.info("Calling createGenre method from controller");
    Genre genre = genreService.createGenre(genreObject);
    if (genre != null) {
        throw new InformationExistException("Genre with" + genre.getName() + "already exist");
    } else {
        return genreService.createGenre(genreObject);
}}
//Calls the service class to allow the game to create a new genre.
@PutMapping(path = "/genre/{genreId}")
public Genre updateGenre(@PathVariable(value = "genreId") Long genreId, @RequestBody Genre genreObject) {
    LOGGER.info("Calling updateGenre method from controller");
    Optional<Genre> genre = Optional.ofNullable(genreService.updateGenre(genreId, genreObject));
    Genre updateGenre = null;
    if (genre.isPresent()) {
        if (genreObject.getName().equals(genre.get().getName())) {
            throw new InformationExistException("Genre " + genre.get().getName() + " already exist");
        } else {
            updateGenre = genreService.updateGenre().get(genreObject);
            updateGenre.setName(genreObject.getName());
        }
    }
    return genreService.save(updateGenre);
}
//Calls the service class to allow a gamer to update a genre by the genre id.
@DeleteMapping(path = "/genre/{genreId}")
public Optional<Genre> deleteGenre(@PathVariable Long genreId) {
    LOGGER.info("Calling deleteGenre method from controller");
    Optional<Genre> genre = genreService.deleteGenre(genreId);
    if (genre.isPresent()) {
        genreService.deleteGenre(genreId);
        return genre;
    } else {
        throw new InformationNotFoundException("Genre with " + genreId + " does not exist");
    }
}
//Calls the service class to allow a gamer to delete a specific genre using the genre id.
}


