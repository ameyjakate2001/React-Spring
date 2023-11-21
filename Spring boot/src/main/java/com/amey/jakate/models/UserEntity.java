package com.amey.jakate.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="users")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;
    private String name;
  private String username;
  private String password;

  private Boolean isAdmin;

    public Boolean getAdmin() {
        return isAdmin;
    }

    public void setAdmin(Boolean admin) {
        isAdmin = admin;
    }

    @Override
    public String toString() {
        return "UserEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", isAdmin=" + isAdmin +
                ", pockemons=" + pockemons +
                '}';
    }

    @OneToMany(mappedBy = "user")
private Set<Review> reviews = new HashSet<Review>();

    public Set<Pockemon> getPockemons() {
        return pockemons;
    }

    public void setPockemons(Set<Pockemon> pockemons) {
        this.pockemons = pockemons;
    }

    @OneToMany(mappedBy = "user")
private Set<Pockemon> pockemons = new HashSet<Pockemon>();



    public Set<Review> getReviews() {
        return reviews;
    }

    public void setReviews(Set<Review> reviews) {
        this.reviews = reviews;
    }




    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
