package com.gamelibrary.gamelibraryapp.controller;


import com.gamelibrary.gamelibraryapp.repository.PublisherRepository;
import com.gamelibrary.gamelibraryapp.service.PublisherService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Logger;

@RestController
@RequestMapping(path = "/api")
public class PublisherController {

    public static final Logger LOGGER = Logger.getLogger(PublisherController.class.getName());
    private PublisherService publisherService;

    public void setPublisherService(PublisherService publisherService){
        this.publisherService = publisherService;
    }

}
