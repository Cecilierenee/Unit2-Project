package com.gamelibrary.gamelibraryapp.service;

import com.gamelibrary.gamelibraryapp.model.Genre;
import com.gamelibrary.gamelibraryapp.repository.GenreRepository;

import java.util.List;
import java.util.logging.Logger;

public class GenreService {

    public static final Logger LOGGER = Logger.getLogger(GenreService.class.getName());
    private GenreRepository genreRepository;
    private GenreRepository gameRepository;

    public List<Genre> getGenres() {
        LOGGER.info("Calling getGenre method from service");
        return genreRepository.findAll();
    }

}
