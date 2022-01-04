package com.gamelibrary.gamelibraryapp.service;

import com.gamelibrary.gamelibraryapp.exception.InformationExistException;
import com.gamelibrary.gamelibraryapp.exception.InformationNotFoundException;
import com.gamelibrary.gamelibraryapp.model.Developer;
import com.gamelibrary.gamelibraryapp.model.Game;
import com.gamelibrary.gamelibraryapp.model.Genre;
import com.gamelibrary.gamelibraryapp.repository.DeveloperRepository;
import com.gamelibrary.gamelibraryapp.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Service
public class DeveloperService {

    private static final Logger LOGGER = Logger.getLogger(DeveloperService.class.getName());

    private DeveloperRepository developerRepository;


    @Autowired
    public void setDeveloperRepository(DeveloperRepository developerRepository){
        this.developerRepository = developerRepository;
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
