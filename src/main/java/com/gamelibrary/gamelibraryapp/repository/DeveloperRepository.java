package com.gamelibrary.gamelibraryapp.repository;

import com.gamelibrary.gamelibraryapp.model.Developer;
import com.gamelibrary.gamelibraryapp.model.Game;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DeveloperRepository extends JpaRepository<Developer,Long> {



    List<Developer> findByUserId(Long userId);

    Optional<Developer> findByIdAndUserId(Long developerId, Long userId);
}
