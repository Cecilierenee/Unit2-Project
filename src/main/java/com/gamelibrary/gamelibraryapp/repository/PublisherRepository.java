package com.gamelibrary.gamelibraryapp.repository;

import com.gamelibrary.gamelibraryapp.model.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PublisherRepository extends JpaRepository<Publisher, Long> {

    List<Publisher> findByUserId(Long userId);
    Optional<Publisher> findByIdAndUserId(Long publisherId, Long userId);
}
