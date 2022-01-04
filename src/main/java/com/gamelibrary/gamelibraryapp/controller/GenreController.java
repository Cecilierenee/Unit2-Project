package com.gamelibrary.gamelibraryapp.controller;


import com.gamelibrary.gamelibraryapp.model.Genre;
import com.gamelibrary.gamelibraryapp.repository.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping(path = "/api")
public class GenreController {

    private static final Logger LOGGER = Logger.getLogger(GenreController.class.getName());
    private GenreRepository genreRepository;

public List<Genre> getGenres() {
    LOGGER.info("Calling getGenre method from controller");
    return genreRepository.findAll();
}

}


