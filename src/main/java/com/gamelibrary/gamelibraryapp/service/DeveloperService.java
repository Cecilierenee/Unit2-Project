package com.gamelibrary.gamelibraryapp.service;

import com.gamelibrary.gamelibraryapp.model.Developer;
import com.gamelibrary.gamelibraryapp.model.Game;
import com.gamelibrary.gamelibraryapp.repository.DeveloperRepository;
import com.gamelibrary.gamelibraryapp.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
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

}
