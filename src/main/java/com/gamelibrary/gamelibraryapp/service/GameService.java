package com.gamelibrary.gamelibraryapp.service;


import com.gamelibrary.gamelibraryapp.exception.InformationExistException;
import com.gamelibrary.gamelibraryapp.exception.InformationNotFoundException;
import com.gamelibrary.gamelibraryapp.model.Developer;
import com.gamelibrary.gamelibraryapp.model.Game;
import com.gamelibrary.gamelibraryapp.model.Genre;
import com.gamelibrary.gamelibraryapp.repository.DeveloperRepository;
import com.gamelibrary.gamelibraryapp.repository.GameRepository;
import com.gamelibrary.gamelibraryapp.repository.GenreRepository;
import com.gamelibrary.gamelibraryapp.security.MyUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;


import java.util.List;

import java.util.Optional;
import java.util.logging.Logger;

@Service
public class GameService {

    private static final Logger LOGGER = Logger.getLogger(GameService.class.getName());
    private GameRepository gameRepository;
    private DeveloperRepository developerRepository;
    private GenreRepository genreRepository;

    @Autowired
    public void setGameRepository(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    @Autowired
    public void setDeveloperRepository(DeveloperRepository developerRepository) {
        this.developerRepository = developerRepository;
    }

    public void setGenreRepository(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    public List<Game> getGames() {
        LOGGER.info("calling getGames method from service");
        MyUserDetails userDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<Game> game = gameRepository.findByUserId(userDetails.getUser().getId());
        if (game.isEmpty()) {
            throw new InformationNotFoundException("No games found for user id " + userDetails.getUser().getId());
        } else {
            return game;
        }


    }

    public Game getGame(Long gameId) {
        LOGGER.info("calling getGame method from service");
        MyUserDetails userDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Game game = gameRepository.findByIdAndUserId(gameId, userDetails.getUser().getId());
        if (game == null) {
            throw new InformationNotFoundException("game with id " + gameId + " is not found for this user");
        } else {
            return game;
        }
    }

    public Game createGame(Long developerId, Long genreId, Game gameObject) {
        LOGGER.info("calling createGame method from service");
        MyUserDetails userDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Developer developer = developerRepository.findByIdAndUserId(userDetails.getUser().getId(), developerId);
        if (developer == null) {
            throw new InformationNotFoundException("developer with id " + developerId + " not found");
        }
        Genre genre = genreRepository.findByIdAndUserId(genreId, userDetails.getUser().getId());
        if (genre == null) {
            throw new InformationNotFoundException("genre with id " + genreId + " not found");
        }
        Game game = gameRepository.findByUserIdAndName(userDetails.getUser().getId(), gameObject.getName());
        if (game != null){
            throw new InformationNotFoundException("game with name " + gameObject.getName() + " already exists");
    }
        gameObject.setDeveloper(developer);
        gameObject.setGenre(genre);
        gameObject.setUser(userDetails.getUser());
        return gameRepository.save(gameObject);


}

    public Game updateGame(Long gameId,Game gameObject) {
        LOGGER.info("calling updateGame method from service");
        MyUserDetails userDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Game game = gameRepository.findByIdAndUserId(gameId, userDetails.getUser().getId());
        if (game != null) {
                throw new InformationExistException("game with id " + gameId + " already exist");
            } else {

                game.setName(gameObject.getName());
                game.setDescription(gameObject.getDescription());
                game.setPrice(gameObject.getPrice());
                game.setRating(gameObject.getRating());
                game.setReleaseDate(gameObject.getReleaseDate());
                return gameRepository.save(game);
            }
    }

    public Game deleteGame(Long gameId) {
        LOGGER.info("calling deleteGame method from service");
        MyUserDetails userDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Game game = gameRepository.findByIdAndUserId(gameId, userDetails.getUser().getId());
        if (game != null) {
            gameRepository.deleteById(gameId);
            return game;
        } else {
            throw new InformationNotFoundException("game with id " + gameId + " is not found");
        }

    }

}
