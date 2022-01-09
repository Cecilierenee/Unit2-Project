package com.gamelibrary.gamelibraryapp.repository;


import com.gamelibrary.gamelibraryapp.model.Genre;
import com.gamelibrary.gamelibraryapp.model.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PublisherRepository extends JpaRepository<Publisher, Long> {

    List<Publisher> findByUserId(Long userId);
    Optional<Publisher> findByIdAndUserId(Long publisherId, Long userId);
    List<Publisher> findByGameId(Long gameId);
    Publisher findByNameAndUserId(String name, Long userId);
    Publisher findByUserIdAndNameAndIdIsNot(Long userId, String gameName, Long publisherId);
}
