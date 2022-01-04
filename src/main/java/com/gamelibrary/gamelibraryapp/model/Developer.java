package com.gamelibrary.gamelibraryapp.model;

import javax.persistence.Entity;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "developers")
public class Developer {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;


   @OneToMany
   @LazyCollection(LazyCollectionOption.FALSE)
   //One Developer can have more than one game
    private List<Game> gameList;



    public Developer() {
    }

    public Developer(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Game> getGameList(){
        return gameList;
    }
    public void setGameList(List<Game> gameList){
        this.gameList = gameList;
    }


}
