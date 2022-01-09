package com.gamelibrary.gamelibraryapp.service;


import com.gamelibrary.gamelibraryapp.exception.InformationExistException;
import com.gamelibrary.gamelibraryapp.exception.InformationNotFoundException;
import com.gamelibrary.gamelibraryapp.model.Game;
import com.gamelibrary.gamelibraryapp.model.Genre;
import com.gamelibrary.gamelibraryapp.repository.DeveloperRepository;
import com.gamelibrary.gamelibraryapp.repository.GameRepository;
import com.gamelibrary.gamelibraryapp.repository.GenreRepository;
import com.gamelibrary.gamelibraryapp.security.MyUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;


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

    public Game createGame(Game gameObject) {
        LOGGER.info("calling createGame method from service");
        MyUserDetails userDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Game game = gameRepository.findByUserIdAndName(userDetails.getUser().getId(), gameObject.getName());
        if (game != null) {
            throw new InformationExistException("game with name " + game.getName() + " already exists");
        } else {
            gameObject.setUser(userDetails.getUser());
            return gameRepository.save(gameObject);
        }
    }


    public Game updateGame(Long gameId, Game gameObject) {
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

    public List<Genre> getGenres(Long gameId) {
        LOGGER.info("Calling getGenre method from service");
        MyUserDetails userDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Game game = gameRepository.findByIdAndUserId(gameId, userDetails.getUser().getId());
        if (game == null) {
            throw new InformationNotFoundException("game with id " + gameId + " " +
                    "not belongs to this user or category does not exist");
        }
        return game.getGenreList();
    }

    public Genre getGenre(Long gameId, Long genreId) {
        LOGGER.info("Calling getGenre method from service");
        MyUserDetails userDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Game game = gameRepository.findByIdAndUserId(gameId, userDetails.getUser().getId());
        if (game == null) {
            throw new InformationNotFoundException("Game with id  " + gameId + " does not exist or does not belong to user");
        }
        Optional<Genre> genre = genreRepository.findByGameId(
                gameId).stream().filter(p -> p.getId().equals(genreId)).findFirst();
        if (!genre.isPresent()) {
            throw new InformationNotFoundException("genre with id " + genreId +
                    " not belongs to this user or item does not exist");
        }
        return genre.get();
    }


    public Genre createGenre(Long gameId, Genre genreObject) {
        LOGGER.info("Calling createGenre method from service");
        MyUserDetails userDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Game game = gameRepository.findByIdAndUserId(gameId, userDetails.getUser().getId());
        if (game == null) {
            throw new InformationNotFoundException(
                    "game with id " + gameId + " not belongs to this user or game does not exist");
        }
        Genre genre = genreRepository.findByNameAndUserId(genreObject.getName(), userDetails.getUser().getId());
        if (genre != null) {
            throw new InformationExistException("genre with name " + genre.getName() + " already exists");
        }
        genreObject.setUser(userDetails.getUser());
        genreObject.setGame(game);
        return genreRepository.save(genreObject);
    }

    public Genre updateGenre(Long gameId, Long genreId, Genre genreObject) {
        LOGGER.info("Calling updateGenre method from service");
        MyUserDetails userDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Game game = gameRepository.findByIdAndUserId(gameId, userDetails.getUser().getId());
        if (game == null) {
            throw new InformationNotFoundException("category with id " + gameId + " does not exist");
        }
        Optional<Genre> genre = genreRepository.findByGameId(gameId).stream()
                .filter(i -> i.getId().equals(genreId)).findFirst();
        if (!genre.isPresent()) {
            throw new InformationNotFoundException("item with id " + genreId + " does not exist");
        }
        Genre genre1 = genreRepository.findByUserIdAndNameAndIdIsNot(userDetails.getUser().getId(),
                genreObject.getName(), genreId);
        if (genre1 != null) {
            throw new InformationExistException("item with id " + genreId + " already exists");
        } else {
            genre.get().setName(genreObject.getName());
            return genreRepository.save(genre.get());
        }
    }

    public void deleteGenre(Long gameId, Long genreId) {
        LOGGER.info("Calling deleteGenre method from controller");
        MyUserDetails userDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Game game = gameRepository.findByIdAndUserId(gameId, userDetails.getUser().getId());
        if (game == null) {
            throw new InformationNotFoundException("Game with " + gameId + " does not exist");
        }
        Optional<Genre> genre = genreRepository.findByGameId(
                gameId).stream().filter(p -> p.getId().equals(genreId)).findFirst();
        if (!genre.isPresent()) {
            throw new InformationNotFoundException("genre with id " + gameId +
                    " not belongs to this user or item does not exist");
        }
        genreRepository.deleteById(genre.get().getId());
    }



}