package com.gamelibrary.gamelibraryapp.service;


import com.gamelibrary.gamelibraryapp.exception.InformationExistException;
import com.gamelibrary.gamelibraryapp.exception.InformationNotFoundException;
import com.gamelibrary.gamelibraryapp.model.Game;
import com.gamelibrary.gamelibraryapp.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;


import java.util.List;

import java.util.Optional;
import java.util.logging.Logger;

@Service
public class GameService {

    private static final Logger LOGGER = Logger.getLogger(GameService.class.getName());
    private GameRepository gameRepository;

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


}
