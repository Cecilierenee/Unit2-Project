package com.gamelibrary.gamelibraryapp.controller;


import com.gamelibrary.gamelibraryapp.exception.InformationExistException;
import com.gamelibrary.gamelibraryapp.exception.InformationNotFoundException;
import com.gamelibrary.gamelibraryapp.model.Genre;
import com.gamelibrary.gamelibraryapp.repository.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@RestController
@RequestMapping(path = "/api")
public class GenreController {
    private static final Logger LOGGER = Logger.getLogger(GenreController.class.getName());
    private GenreRepository genreRepository;


@GetMapping(path = "/genre")
public List<Genre> getGenres() {
    LOGGER.info("Calling getGenres method from controller");
    return genreRepository.findAll();
}
@GetMapping(path = "/genre/{genreId}")
public Optional getGenre(@PathVariable Long genreId) {
    LOGGER.info("Calling getGenre method from controller");
    Optional<Genre> genre = genreRepository.findById(genreId);
    if (genre.isPresent()) {
        return genre;
    } else {
        throw new InformationNotFoundException("Genre with " +genreId + "Does not exist");
    }
}
@PostMapping(path = "/genre")
public Genre createGenre(@PathVariable Genre genreObject) {
    LOGGER.info("Calling createGenre method from controller");
    Genre genre = genreRepository.findByName(genreObject.getName());
    if (genre != null) {
        throw new InformationExistException("Genre with" + genre.getName() + "already exist");
    } else {
        return genreRepository.save(genreObject);
}

public Genre updateGenre(@PathVariable genre genreId) Long genreId, @RequestBody genre genreObject) {
        LOGGER.info("Calling updateGenre method from controller");
        Optional<Genre> genre = genreRepository.findById(genreId);
        if (genre.isPresent()) {
            if (genreObject.getName().equals(genre.get().getName())) {
                throw new InformationExistException("Genre " + genre.get().getName() + " already exist");
            } else {
                Genre updateGenre = genreRepository.findById(genreId).get();
                updateGenre.setName(genreObject.getName());
            }
        }
    }

}


