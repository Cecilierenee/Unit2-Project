package com.gamelibrary.gamelibraryapp.service;

import com.gamelibrary.gamelibraryapp.model.Publisher;
import com.gamelibrary.gamelibraryapp.repository.PublisherRepository;

import java.util.List;
import java.util.logging.Logger;

public class PublisherService {
    private PublisherRepository publisherRepository;

    public static final Logger LOGGER = Logger.getLogger(PublisherService.class.getName());

    public void setPublisherRepository(PublisherRepository publisherRepository){
        this.publisherRepository = publisherRepository;
    }

    public List<Publisher> getPublishers(){

    }
}
