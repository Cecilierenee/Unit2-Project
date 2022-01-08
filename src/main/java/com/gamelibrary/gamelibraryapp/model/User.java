package com.gamelibrary.gamelibraryapp.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
public class User {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userName;

    @Column(unique = true)
    private String emailAddress;

    @Column
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    // one user can have only one profile
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "profile_id", referencedColumnName = "id")
    private UserProfile userProfile;

    // user can have more than one developer
    @OneToMany(mappedBy = "user")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Developer> developerList;

    // user can have more than one game
    @OneToMany(mappedBy = "user")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Game> gameList;

    // user can have more than one genre
    @OneToMany(mappedBy = "user")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Genre> genreList;

    // user can have more than one game
    @OneToMany(mappedBy = "user")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Publisher> publisherList;


    public UserProfile getUserProfile() {
        return userProfile;
    }

    public void setUserProfile(UserProfile userProfile) {
        this.userProfile = userProfile;
    }


    public User() {
    }

    public User(Long id, String userName, String emailAddress, String password) {
        this.id = id;
        this.userName = userName;
        this.emailAddress = emailAddress;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Developer> getDeveloperList() {
        return developerList;
    }

    public void setDeveloperList(List<Developer> developerList) {
        this.developerList = developerList;
    }


    public List<Game> getGameList() {
        return gameList;
    }

    public void setGameList(List<Game> gameList) {
        this.gameList = gameList;
    }

    public List<Genre> getGenreListList() {
        return genreListList;
    }

    public void setGenreListList(List<Genre> genreListList) {
        this.genreListList = genreListList;
    }

    public List<Publisher> getPublisherListList() {
        return publisherListList;
    }

    public void setPublisherListList(List<Publisher> publisherListList) {
        this.publisherListList = publisherListList;
    }
}