package com.gamelibrary.gamelibraryapp.service;

import com.gamelibrary.gamelibraryapp.exception.InformationExistException;
import com.gamelibrary.gamelibraryapp.exception.InformationNotFoundException;
import com.gamelibrary.gamelibraryapp.model.Publisher;
import com.gamelibrary.gamelibraryapp.repository.PublisherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Service
public class PublisherService {
    private PublisherRepository publisherRepository;

    public static final Logger LOGGER = Logger.getLogger(PublisherService.class.getName());

    @Autowired
    public void setPublisherRepository(PublisherRepository publisherRepository){
        this.publisherRepository = publisherRepository;
    }
    //Get all publishers in the publisher model
    public List<Publisher> getPublishers(){
    LOGGER.info("Calling getPublishers method from service");
    return publisherRepository.findAll();
    }

    //Get specific publisher in the model
    public Optional<Publisher> getPublisher(Long publisherId) {
        LOGGER.info("Calling getPublisher method from service");
        Optional<Publisher> publisher = publisherRepository.findById(publisherId);
        if (publisher.isPresent()) {
            return publisher;
        } else {
            throw new InformationNotFoundException("Publisher with id " + publisherId + "Does not exist" );
        }
    }
    //Create a publisher in the model
    public Publisher createPublisher(Publisher publisherObject) {
        LOGGER.info("Calling createPublisher method from service");
        Publisher publisher = publisherRepository.findByName(publisherObject.getName());
        if (publisher != null) {
            throw new InformationExistException("Genre with" + publisher.getName() + "already exist");
        } else {
            return publisherRepository.save(publisherObject);
        }
    }
    //Update publisher in the publisher model
    public Publisher updatePublisher(Long publisherId, Publisher publisherObject) {
        LOGGER.info("Calling updatePublisher method from service");
        Optional <Publisher> publisher = publisherRepository.findById(publisherId);
        if(publisher.isPresent()) {
            if (publisherObject.getName().equals(publisher.get().getName())) {
                throw new InformationExistException("Genre " + publisher.get().getName() + " already exist");
            } else {
                Publisher updatePublisher = publisherRepository.findById(publisherId).get();
                updatePublisher.setName(publisherObject.getName());

                return publisherRepository.save(publisherObject);
            }
        } else {
            throw new InformationNotFoundException("Can not update " + publisherId + "It does not exist");
        }
    }
    //Delete a publisher from the publisher model.
    public Optional<Publisher> deletePublisher(Long publisherId) {
        LOGGER.info("Calling deletePublisher method from controller");
        Optional<Publisher> publisher = publisherRepository.findById(publisherId);
        if (publisher.isPresent()) {
            publisherRepository.deleteById(publisherId);
            return publisher;
        } else {
            throw new InformationNotFoundException("Genre with " + publisherId + " does not exist");

        }
    }
}


