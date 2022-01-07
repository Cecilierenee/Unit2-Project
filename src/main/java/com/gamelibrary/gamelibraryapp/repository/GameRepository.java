package com.gamelibrary.gamelibraryapp.repository;

import com.gamelibrary.gamelibraryapp.model.Game;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GameRepository extends JpaRepository<Game, Long> {
    Game findByName(String gameName);

    List<Game> findByUserId(Long userId);

    Game findByIdAndUserId(Long gameId, Long userId);

    Game findByUserIdAndName(Long userId, String gameName);
}
