package com.gamelibrary.gamelibraryapp.controller;


import com.gamelibrary.gamelibraryapp.exception.InformationExistException;
import com.gamelibrary.gamelibraryapp.exception.InformationNotFoundException;
import com.gamelibrary.gamelibraryapp.model.Genre;
import com.gamelibrary.gamelibraryapp.repository.GenreRepository;
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
    private GenreRepository genreRepository;

@Autowired
    public void setGenreService(GenreService genreService) {
        this.genreService;
    }


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
}}
@PutMapping(path = "/genre/{genreId}")
public Genre updateGenre(@PathVariable(value = "genreId") Long genreId, @RequestBody Genre genreObject) {
    LOGGER.info("Calling updateGenre method from controller");
    Optional<Genre> genre = genreRepository.findById(genreId);
    Genre updateGenre = null;
    if (genre.isPresent()) {
        if (genreObject.getName().equals(genre.get().getName())) {
            throw new InformationExistException("Genre " + genre.get().getName() + " already exist");
        } else {
            updateGenre = genreRepository.findById(genreId).get();
            updateGenre.setName(genreObject.getName());
        }
    }
    return genreRepository.save(updateGenre);
}
@DeleteMapping(path = "/genre/{genreId}")
public Optional<Genre> deleteGenre(@PathVariable Long genreId) {
    LOGGER.info("Calling deleteGenre method from controller");
    Optional<Genre> genre = genreRepository.findById(genreId);
    if (genre.isPresent()) {
        genreRepository.deleteById(genreId);
        return genre;
    } else {
        throw new InformationNotFoundException("Genre with " + genreId + " does not exist");
    }
}

}


