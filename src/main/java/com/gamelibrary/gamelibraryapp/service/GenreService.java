//package com.gamelibrary.gamelibraryapp.service;
//
//import com.gamelibrary.gamelibraryapp.exception.InformationExistException;
//import com.gamelibrary.gamelibraryapp.exception.InformationNotFoundException;
//import com.gamelibrary.gamelibraryapp.model.Game;
//import com.gamelibrary.gamelibraryapp.model.Genre;
//import com.gamelibrary.gamelibraryapp.repository.GameRepository;
//import com.gamelibrary.gamelibraryapp.repository.GenreRepository;
//import com.gamelibrary.gamelibraryapp.security.MyUserDetails;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.stereotype.Service;
//
//
//import java.util.List;
//import java.util.Optional;
//import java.util.logging.Logger;
//
//@Service
//public class GenreService {
//
//    public static final Logger LOGGER = Logger.getLogger(GenreService.class.getName());
//    private GenreRepository genreRepository;
//    private GameRepository gameRepository;
//
//    @Autowired
//    public void setGenreRepository(GenreRepository genreRepository) {
//        this.genreRepository = genreRepository;
//    }
//
//    @Autowired
//    public void setGameRepository(GameRepository gameRepository) {
//        this.gameRepository = gameRepository;
//    }
//
//    public List<Genre> getGenres() {
//        LOGGER.info("Calling getGenre method from service");
//        return genreRepository.findAll();
//    }
//
//    public Genre getGenre(Long genreId) {
//        LOGGER.info("Calling getGenre method from service");
//        MyUserDetails userDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        Genre genre =  genreRepository.findByIdAndUserId(genreId, userDetails.getUser().getId());
//        if (genre == null) {
//            throw new InformationNotFoundException("Genre with " +genreId + "Does not exist");
//        } else {
//            return genre;
//        }
//}
//    public List<Game> getGamesInGenre(Long genreId) {
//        LOGGER.info("Calling getGamesInGenre method from service");
//        MyUserDetails userDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        Genre genre = genreRepository.findByIdAndUserId(genreId, userDetails.getUser().getId());
//        if (genre != null) {
//            return genre.getGameList();
//        } else {
//            throw new InformationNotFoundException("Genre with id " + genreId + " not found");
//        }
//    }
//
//    public Genre createGenre(Genre genreObject) {
//        LOGGER.info("Calling createGenre method from service");
//        MyUserDetails userDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        Genre genre = genreRepository.findByUserIdAndName(userDetails.getUser().getId(), genreObject.getName());
//        if (genre != null) {
//            throw new InformationExistException("Genre with" + genre.getName() + "already exist");
//        } else {
//            genreObject.setUser(userDetails.getUser());
//            return genreRepository.save(genreObject);
//        }}
//    public Genre updateGenre(Long genreId, Genre genreObject) {
//        LOGGER.info("Calling updateGenre method from service");
//        MyUserDetails userDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        Genre genre = genreRepository.findByIdAndUserId(genreId, userDetails.getUser().getId());
//        if(genre != null) {
//            if (genreObject.getName().equals(genre.getName())) {
//                throw new InformationExistException("Genre " + genre.getName() + " already exist");
//            } else {
//               genre.setName(genreObject.getName());
//               genre.setUser(userDetails.getUser());
//               return genreRepository.save(genre);
//            }
//        } else {
//            throw new InformationNotFoundException("Can not update " + genreId + "It does not exist");
//        }
//    }
//    public Genre deleteGenre(Long genreId) {
//        LOGGER.info("Calling deleteGenre method from controller");
//        MyUserDetails userDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        Genre genre = genreRepository.findByIdAndUserId(genreId, userDetails.getUser().getId());
//        if (genre != null) {
//            genreRepository.deleteById(genreId);
//            return genre;
//        } else {
//            throw new InformationNotFoundException("Genre with " + genreId + " does not exist");
//        }
//
//}}
