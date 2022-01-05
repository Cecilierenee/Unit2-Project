package com.gamelibrary.gamelibraryapp.service;

import com.gamelibrary.gamelibraryapp.exception.InformationExistException;
import com.gamelibrary.gamelibraryapp.exception.InformationNotFoundException;
import com.gamelibrary.gamelibraryapp.model.Game;
import com.gamelibrary.gamelibraryapp.model.Genre;
import com.gamelibrary.gamelibraryapp.repository.GenreRepository;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Service
public class GenreService {

    public static final Logger LOGGER = Logger.getLogger(GenreService.class.getName());
    private GenreRepository genreRepository;
    private GenreRepository gameRepository;

    public List<Genre> getGenres() {
        LOGGER.info("Calling getGenre method from service");
        List<Genre> genres = genreRepository.findAll();
        return genres;
    }

    public Optional getGenre(Long genreId) {
        LOGGER.info("Calling getGenre method from service");
        Optional<Genre> genre = genreRepository.findById(genreId);
        if (genre.isPresent()) {
            return genre;
        } else {
            throw new InformationNotFoundException("Genre with " +genreId + "Does not exist");
        }
}
    public List<Game> getGamesInGenre(Long genreId) {
        LOGGER.info("Calling getGamesInGenre method from service");
        Genre genre = genreRepository.findById(genreId).get();
        if(genre != null) {
            return genre.gameList;
        } else {
            throw new InformationNotFoundException("Genre with " + genreId + " does not exist");
        }
    }
    public Genre createGenre(Genre genreObject) {
        LOGGER.info("Calling createGenre method from service");
        Genre genre = genreRepository.findByName(genreObject.getName());
        if (genre != null) {
            throw new InformationExistException("Genre with" + genre.getName() + "already exist");
        } else {
            return genreRepository.save(genreObject);
        }}
    public Genre updateGenre(Long genreId, Genre genreObject) {
        LOGGER.info("Calling updateGenre method from service");
        Genre genre = genreRepository.findById(genreId);
        if(genre != null) {
            if (genreObject.getName().equals(genre.get().getName())) {
                throw new InformationExistException("Genre " + genre.get().getName() + " already exist");
            } else {
                genre.setName(genreObject.getName());
                return genreRepository.save(genreObject);
            }
        } else {
            throw new InformationNotFoundException("Can not update " + genreId + "It does not exist");
        }
    }
    public Optional<Genre> deleteGenre(Long genreId) {
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
