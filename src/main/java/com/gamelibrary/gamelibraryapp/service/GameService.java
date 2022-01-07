package com.gamelibrary.gamelibraryapp.service;


import com.gamelibrary.gamelibraryapp.exception.InformationExistException;
import com.gamelibrary.gamelibraryapp.exception.InformationNotFoundException;
import com.gamelibrary.gamelibraryapp.model.Developer;
import com.gamelibrary.gamelibraryapp.model.Game;
import com.gamelibrary.gamelibraryapp.model.Genre;
import com.gamelibrary.gamelibraryapp.repository.DeveloperRepository;
import com.gamelibrary.gamelibraryapp.repository.GameRepository;
import com.gamelibrary.gamelibraryapp.repository.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
    public void setGameRepository(GameRepository gameRepository){
        this.gameRepository = gameRepository;
    }

    public List<Game> getGames() {
        LOGGER.info("calling getGames method from service");
        return gameRepository.findAll();

    }

    public Optional<Game> getGame(Long gameId) {
        LOGGER.info("calling getGame method from service");
        Optional<Game> game = gameRepository.findById(gameId);
        if (game.isPresent()) {
            return game;
        } else {
            throw new InformationNotFoundException("game with id " + gameId + " is not found");
        }
    }

    public Game createGame(Game gameObject) {
        LOGGER.info("calling createGame method from service");
        Game game = gameRepository.findByName(gameObject.getName());
        if (game != null) {
            throw new InformationExistException("Game with name " + game.getName() + " already exists");
        } else {
            return gameRepository.save(gameObject);
        }
    }

    public Game updateGame(Long gameId, @RequestBody Game gameObject) {
        LOGGER.info("calling updateGame method from service");
        Optional<Game> game = gameRepository.findById(gameId);
        if (game.isPresent()) {
            if (gameObject.getName().equals(game.get().getName())) {
                throw new InformationExistException("game " + game.get().getName() + " already exist");
            } else {
                Game updateGame = gameRepository.findById(gameId).get();
                updateGame.setName(gameObject.getName());
                updateGame.setDescription(gameObject.getDescription());
                updateGame.setPrice(gameObject.getPrice());
                updateGame.setRating(gameObject.getRating());
                updateGame.setReleaseDate(gameObject.getReleaseDate());
                return gameRepository.save(updateGame);
            }
        } else {
            throw new InformationNotFoundException("game with id " + gameId + " not found");
        }

    }
    public Optional<Game> deleteGame(Long gameId) {
        LOGGER.info("calling deleteGame method from service");
        Optional<Game> game = gameRepository.findById(gameId);
        if (game.isPresent()) {
            gameRepository.deleteById(gameId);
            return game;
        } else {
            throw new InformationNotFoundException("game with id " + gameId + " is not found");
        }

    }

    public List<Genre> getGenres() {
        LOGGER.info("Calling getGenre method from service");
        return genreRepository.findAll();
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
    public Optional<Game> getGamesInGenre(Long genreId) {
        LOGGER.info("Calling getGamesInGenre method from service");
        Genre genre = genreRepository.findById(genreId).get();
        if(genre != null) {
            return gameRepository.findById(genreId);
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
        Optional<Genre> genre = genreRepository.findById(genreId);
        if(genre.isPresent()) {
            if (genreObject.getName().equals(genre.get().getName())) {
                throw new InformationExistException("Genre " + genre.get().getName() + " already exist");
            } else {
                genre.get().setName("genreObject");
                return genreRepository.save(genreObject);
            }
        } else {
            throw new InformationNotFoundException("Can not update " + genreId + "It does not exist");
        }
    }
    public Optional<Genre> deleteGenre(Long genreId) {
        LOGGER.info("Calling deleteGenre method from controller");
        if (genreRepository.findById(genreId).isPresent()) {
            genreRepository.deleteById(genreId);
        } else {
            throw new InformationNotFoundException("Genre with " + genreId + " does not exist");
        }
        return null;
    }















    public List<Developer> getDevelopers() {
        LOGGER.info("calling getDevelopers method from service");
        return developerRepository.findAll();

    }

    public List<Game> getDeveloperGames(Long developerId){
        LOGGER.info("calling getDeveloperGames method from service");
        Optional<Developer> developer = getDeveloper(developerId);
        return developer.get().getGameList();
    }

    public Game getDeveloperGame(Long developerId, Long gameId){
        LOGGER.info("calling getDeveloperGame method from service");
        Optional<Game> game = getDeveloperGames(developerId).stream().filter(x -> x.getId().equals(gameId)).findFirst();
        if (game.isPresent()){
            return game.get();
        }else{
            throw new InformationNotFoundException("The game with id " + gameId + " does not exist");
        }

    }


    public Optional<Developer> getDeveloper(Long developerId) {
        LOGGER.info("calling getDeveloper method from service");
        Optional<Developer> developer = developerRepository.findById(developerId);
        if (developer.isPresent()) {
            return developer;
        } else {
            throw new InformationNotFoundException("game with id " + developerId + " is not found");
        }
    }




    public Developer createDeveloper(Developer developerObject) {
        LOGGER.info("calling createDeveloper method from service");
        Developer developer = developerRepository.findByName(developerObject.getName());
        if (developer != null) {
            throw new InformationExistException("Game with name " + developer.getName() + " already exists");
        } else {
            return developerRepository.save(developerObject);
        }
    }

    public Developer updateDeveloper(Long developerId,  Developer developerObject) {
        LOGGER.info("calling updateDeveloper method from service");
        Optional<Developer> developer = developerRepository.findById(developerId);
        if (developer.isPresent()) {
            if (developerObject.getName().equals(developer.get().getName())) {
                throw new InformationExistException("developer " + developer.get().getName() + " already exist");
            } else {
                Developer updateDeveloper = developerRepository.findById(developerId).get();
                updateDeveloper.setName(developerObject.getName());

                return developerRepository.save(updateDeveloper);
            }
        } else {
            throw new InformationNotFoundException("developer with id " + developerId + " not found");
        }

    }

    public Optional<Developer> deleteDeveloper(Long developerId) {
        LOGGER.info("calling deleteDeveloper method from service");
        Optional<Developer> developer = developerRepository.findById(developerId);
        if (developer.isPresent()) {
            developerRepository.deleteById(developerId);
            return developer;
        } else {
            throw new InformationNotFoundException("developer with id " + developerId + " is not found");
        }

    }

}
