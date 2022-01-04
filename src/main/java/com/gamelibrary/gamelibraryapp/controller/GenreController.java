package com.gamelibrary.gamelibraryapp.controller;


import com.gamelibrary.gamelibraryapp.exception.InformationNotFoundException;
import com.gamelibrary.gamelibraryapp.model.Genre;
import com.gamelibrary.gamelibraryapp.repository.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

public Optional getGenre(@PathVariable Long genreId) {
    LOGGER.info("Calling getGenre method from controller");
    Optional<Genre> genre = genreRepository.findById(genreId);
    if (genre.isPresent()) {
        return genre;
    } else {
        throw new InformationNotFoundException("Genre with " +genreId + "Does not exist");
    }
}

}


