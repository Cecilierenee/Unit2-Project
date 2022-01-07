package com.gamelibrary.gamelibraryapp.service;

import com.gamelibrary.gamelibraryapp.exception.InformationExistException;
import com.gamelibrary.gamelibraryapp.exception.InformationNotFoundException;
import com.gamelibrary.gamelibraryapp.model.Publisher;
import com.gamelibrary.gamelibraryapp.repository.PublisherRepository;
import com.gamelibrary.gamelibraryapp.security.MyUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Service
public class PublisherService {
    private PublisherRepository publisherRepository;

    public static final Logger LOGGER = Logger.getLogger(PublisherService.class.getName());

    @Autowired
    public void setPublisherRepository(PublisherRepository publisherRepository) {
        this.publisherRepository = publisherRepository;
    }

    //Get all publishers in the publisher model
    public List<Publisher> getPublishers() {
        LOGGER.info("Calling getPublishers method from service");
        MyUserDetails userDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return publisherRepository.findByUserId(userDetails.getUser().getId());
    }

    //Get specific publisher in the model
    public Publisher getPublisher(Long publisherId) {
        LOGGER.info("Calling getPublisher method from service");
        MyUserDetails userDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<Publisher> publisher = publisherRepository.findByIdAndUserId(publisherId, userDetails.getUser().getId());
        if (publisher.isPresent()) {
            return publisher.get();
        } else {
            throw new InformationNotFoundException("Publisher with id " + publisherId + "Does not exist");
        }
    }

    //Create a publisher in the model
    public Publisher createPublisher(Publisher publisherObject) {
        LOGGER.info("Calling createPublisher method from service");
        MyUserDetails userDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        publisherObject.setUser(userDetails.getUser());
        return publisherRepository.save(publisherObject);
    }

    //Update publisher in the publisher model
    public Publisher updatePublisher(Long publisherId, Publisher publisherObject) {
        LOGGER.info("Calling updatePublisher method from service");
        Publisher publisher = getPublisher(publisherId);
        publisher.setName(publisherObject.getName());
        return publisherRepository.save(publisher);
    }

    //Delete a publisher from the publisher model.
    public void deletePublisher(Long publisherId) {
        LOGGER.info("Calling deletePublisher method from service");
        MyUserDetails userDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (publisherRepository.findByIdAndUserId(publisherId, userDetails.getUser().getId()).isPresent()) {
            publisherRepository.deleteById(publisherId);
        } else {
            throw new InformationNotFoundException("Publisher with " + publisherId + " does not exist");
        }
    }
}


