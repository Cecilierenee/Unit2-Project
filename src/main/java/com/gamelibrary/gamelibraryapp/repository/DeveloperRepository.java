package com.gamelibrary.gamelibraryapp.repository;

import com.gamelibrary.gamelibraryapp.model.Developer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeveloperRepository extends JpaRepository<Developer,Long> {

    Developer findByName(String developerName);
}
