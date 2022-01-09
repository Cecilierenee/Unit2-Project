package com.gamelibrary.gamelibraryapp.repository;

import com.gamelibrary.gamelibraryapp.model.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GenreRepository extends JpaRepository<Genre, Long> {

    List<Genre> findByGameId(Long gameId);
    Genre findByIdAndUserId(Long genreId, Long userId);
    Genre findByNameAndUserId(String name, Long userId);
    Genre findByUserIdAndNameAndIdIsNot(Long userId, String gameName, Long genreId);
}
