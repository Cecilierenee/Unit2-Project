package com.gamelibrary.gamelibraryapp.service;

import com.gamelibrary.gamelibraryapp.exception.InformationNotFoundException;
import com.gamelibrary.gamelibraryapp.model.Genre;
import com.gamelibrary.gamelibraryapp.repository.GenreRepository;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

public class GenreService {

    public static final Logger LOGGER = Logger.getLogger(GenreService.class.getName());
    private GenreRepository genreRepository;
    private GenreRepository gameRepository;

    public List<Genre> getGenres() {
        LOGGER.info("Calling getGenre method from service");
        return genreRepository.findAll();
    }

    public Optional getGenre() {
        LOGGER.info("Calling getGenre method from controller");
        Optional<Genre> genre = genreRepository.findById(genreId);
        if (genre.isPresent()) {
            return genre;
        } else {
            throw new InformationNotFoundException("Genre with " +genreId + "Does not exist");
        }
}
    
}
