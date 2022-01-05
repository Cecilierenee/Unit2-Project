package com.gamelibrary.gamelibraryapp.controller;


import com.gamelibrary.gamelibraryapp.model.Publisher;
import com.gamelibrary.gamelibraryapp.repository.PublisherRepository;
import com.gamelibrary.gamelibraryapp.service.PublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
        LOGGER.info("Calling getPublishers method from controller");
        return publisherService.getPublishers();
    }

    @GetMapping(path = "/publisher/{publisherId}")
    public Publisher getPublisher(@PathVariable Long publisherId){
        LOGGER.info("Calling getPublisher method from controller");
        return publisherService.getPublisher(publisherId);
    }

    @PostMapping(path = "/publisher")
    public Publisher createPublisher(@RequestBody Publisher publisherObject){
        LOGGER.info("Calling createPublisher method from controller");
        return publisherService.createPublisher(publisherObject);
    }

    @PutMapping(path = "/publisher/{publisherId}")
    public Publisher updatePublisher(@PathVariable Long publisherId, @RequestBody Publisher publisherObject){
        LOGGER.info("Calling updatePublisher method from controller");
        return publisherService.updatePublisher(publisherId, publisherObject);
    }

    public void deletePublisher(@PathVariable Long publisherId){
        LOGGER.info("Calling deletePublisher method from controller");
        publisherService.deletePublisher(publisherId);
    }
}
