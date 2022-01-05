package com.gamelibrary.gamelibraryapp.controller;


import com.gamelibrary.gamelibraryapp.model.Publisher;
import com.gamelibrary.gamelibraryapp.repository.PublisherRepository;
import com.gamelibrary.gamelibraryapp.service.PublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping(path = "/api")
public class PublisherController {

    public static final Logger LOGGER = Logger.getLogger(PublisherController.class.getName());
    private PublisherService publisherService;

    @Autowired
    public void setPublisherService(PublisherService publisherService){
        this.publisherService = publisherService;
    }

    @GetMapping(path = "/publisher")
    public List<Publisher> getPublishers(){
        LOGGER.info("Calling getPublisher method from controller");
        return publisherService.getPublishers();
    }
}
