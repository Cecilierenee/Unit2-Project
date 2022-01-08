package com.gamelibrary.gamelibraryapp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "publishers")
public class Publisher {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @OneToMany(mappedBy = "publisher", orphanRemoval = true) // One publisher can have many developers
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Developer> developerList;

    /********** add user **********/
    // many categories belong to a one user
    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;
    /********** end of user **********/



    public Publisher() {
    }

    public Publisher(Long id, String name) {
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

    /********** user getters and setters **********/
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    /********** user getters and setters **********/

    public List<Developer> getDeveloperList() {
        return developerList;
    }

    public void setDeveloperList(List<Developer> developerList) {
        this.developerList = developerList;
    }


}
