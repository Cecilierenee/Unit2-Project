package com.gamelibrary.gamelibraryapp.repository;

import com.gamelibrary.gamelibraryapp.model.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface GenreRepository extends JpaRepository<Genre, Long> {

    List<Genre> findByUserId(Long userId);
    Genre findByIdAndUserId(Long genreId, Long userId);
    Genre findByUserIdAndName(Long userId, String genreName);
}
