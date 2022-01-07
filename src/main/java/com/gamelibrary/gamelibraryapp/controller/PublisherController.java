package com.gamelibrary.gamelibraryapp.controller;


import com.gamelibrary.gamelibraryapp.model.Publisher;
import com.gamelibrary.gamelibraryapp.repository.PublisherRepository;
import com.gamelibrary.gamelibraryapp.service.PublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;
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

    //Get all publishers
    @GetMapping(path = "/publisher")
    public List<Publisher> getPublishers(){
        LOGGER.info("Calling getPublishers method from controller");
        return publisherService.getPublishers();
    }

    //Get a single publisher
    @GetMapping(path = "/publisher/{publisherId}")
    public Publisher getPublisher(@PathVariable Long publisherId){
        LOGGER.info("Calling getPublisher method from controller");
        return publisherService.getPublisher(publisherId);
    }

    //Create a publisher
    @PostMapping(path = "/publisher")
    public Publisher createPublisher(@RequestBody Publisher publisherObject){
        LOGGER.info("Calling createPublisher method from controller");
        return publisherService.createPublisher(publisherObject);
    }

    //Update a single publisher
    @PutMapping(path = "/publisher/{publisherId}")
    public Publisher updatePublisher(@PathVariable Long publisherId, @RequestBody Publisher publisherObject){
        LOGGER.info("Calling updatePublisher method from controller");
        return publisherService.updatePublisher(publisherId, publisherObject);
    }

    //Delete a single publisher
    @DeleteMapping(path = "/publisher/{publisherId}")
    public ResponseEntity<HashMap> deletePublisher(@PathVariable Long publisherId){
        LOGGER.info("Calling deletePublisher method from controller");
        publisherService.deletePublisher(publisherId);
        HashMap responseMessage = new HashMap();
        responseMessage.put("status", "publisher: " +publisherId + " was deleted");
        return new ResponseEntity<HashMap>(responseMessage, HttpStatus.OK);
    }
}
