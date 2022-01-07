package com.gamelibrary.gamelibraryapp.repository;

import com.gamelibrary.gamelibraryapp.model.Developer;
import com.gamelibrary.gamelibraryapp.model.Game;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DeveloperRepository extends JpaRepository<Developer,Long> {

    Developer findByName(String developerName);

    List<Developer> findByUserId(Long userId);

    Developer findByIdAndUserId(Long developerId, Long userId);
}
