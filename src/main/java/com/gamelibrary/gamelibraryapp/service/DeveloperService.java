package com.gamelibrary.gamelibraryapp.service;

import com.gamelibrary.gamelibraryapp.exception.InformationExistException;
import com.gamelibrary.gamelibraryapp.exception.InformationNotFoundException;
import com.gamelibrary.gamelibraryapp.model.Developer;
import com.gamelibrary.gamelibraryapp.model.Game;
import com.gamelibrary.gamelibraryapp.model.Genre;
import com.gamelibrary.gamelibraryapp.repository.DeveloperRepository;
import com.gamelibrary.gamelibraryapp.repository.GameRepository;
import com.gamelibrary.gamelibraryapp.repository.PublisherRepository;
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
public class DeveloperService {

    private static final Logger LOGGER = Logger.getLogger(DeveloperService.class.getName());

    private DeveloperRepository developerRepository;
    private PublisherRepository publisherRepository;


    @Autowired
    public void setDeveloperRepository(DeveloperRepository developerRepository){
        this.developerRepository = developerRepository;
    }

    @Autowired
    public void setPublisherRepository(PublisherRepository publisherRepository){
        this.publisherRepository = publisherRepository;
    }

    public List<Developer> getDevelopers() {
        LOGGER.info("calling getDevelopers method from service");
        MyUserDetails userDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return developerRepository.findByUserId(userDetails.getUser().getId());
    }

    public List<Game> getDeveloperGames(Long developerId){
        LOGGER.info("calling getDeveloperGames method from service");
        Developer developer = getDeveloper(developerId);
        if(developer != null) {
            return developer.getGameList();
        }else{
            throw new InformationNotFoundException("The developer with id " + developerId + " does not exist");
        }
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


    public Developer getDeveloper(Long developerId) {
        LOGGER.info("calling getDeveloper method from service");
        MyUserDetails userDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<Developer> developer = Optional.ofNullable(developerRepository.findByIdAndUserId(developerId, userDetails.getUser().getId()));
        if (developer.isPresent()) {
            return developer.get();
        } else {
            throw new InformationNotFoundException("developer with id " + developerId + " is not found");
        }
    }




    public Developer createDeveloper(Developer developerObject) {
        LOGGER.info("calling createDeveloper method from service");
        MyUserDetails userDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        developerObject.setUser(userDetails.getUser());
        return developerRepository.save(developerObject);
    }

    public Developer updateDeveloper(Long developerId,  Developer developerObject) {
        LOGGER.info("calling updateDeveloper method from service");
        MyUserDetails userDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Developer developer = developerRepository.findByIdAndUserId(developerId, userDetails.getUser().getId());
        if (developer == null) {
                throw new InformationExistException("developer with id  " + developerId + " not found");
            } else {
                developer.setName(developerObject.getName());
                developer.setUser(userDetails.getUser());
                return developerRepository.save(developer);
            }
    }

    public Optional<Developer> deleteDeveloper(Long developerId) {
        LOGGER.info("calling deleteDeveloper method from service");
        MyUserDetails userDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<Developer> developer = developerRepository.findById(developerId);
        if (developer.isPresent()) {
            developerRepository.deleteById(developerId);
            return developer;
        } else {
            throw new InformationNotFoundException("developer with id " + developerId + " is not found");
        }

    }



}
