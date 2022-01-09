package com.gamelibrary.gamelibraryapp.repository;

import com.gamelibrary.gamelibraryapp.model.Developer;
import com.gamelibrary.gamelibraryapp.model.Game;
import com.gamelibrary.gamelibraryapp.model.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DeveloperRepository extends JpaRepository<Developer,Long> {


    List<Developer> findByGameId(Long gameId);
    List<Developer> findByUserId(Long userId);
    Developer findByNameAndUserId(String name, Long userId);
    Developer findByUserIdAndNameAndIdIsNot(Long userId, String gameName, Long developerId);

    Optional<Developer> findByIdAndUserId(Long developerId, Long userId);
}
