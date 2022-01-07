package com.gamelibrary.gamelibraryapp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "games")
public class Game {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private String description;

    @Column
    private int price;

    @Column
    private String releaseDate;

    @Column
    private char rating;

@JsonIgnore
@ManyToOne
@JoinColumn(name = "developer_id")
private Developer developer;

@JsonIgnore
@ManyToOne
@JoinColumn(name = "genre_id")
private Genre genre;

    /********** add user **********/
    // many categories belong to a one user
    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;
    /********** end of user **********/

    /********** user getters and setters **********/
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    /********** user getters and setters **********/


    public Game(Long id, String name, String description, int price, String releaseDate, char rating) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.releaseDate = releaseDate;
        this.rating = rating;
    }

    public Game() {
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public char getRating() {
        return rating;
    }

    public void setRating(char rating) {
        this.rating = rating;
    }
}
