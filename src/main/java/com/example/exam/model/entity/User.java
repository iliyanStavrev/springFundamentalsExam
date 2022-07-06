package com.example.exam.model.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class User extends BaseEntity{

    private String username;
    private String password;
    private String email;
    private List<Song> playlist;

//    public User() {
//        this.playlist = new ArrayList<>();
//    }

    @Column(nullable = false,unique = true)
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }


    @Column(nullable = false,unique = true)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(nullable = false)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    public List<Song> getPlaylist() {
        return playlist;
    }
    public void setPlaylist(List<Song> playlist) {
        this.playlist = playlist;
    }

}
