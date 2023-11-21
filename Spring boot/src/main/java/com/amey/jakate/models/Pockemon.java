package com.amey.jakate.models;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;



@Entity
public class Pockemon {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private int id;

   @Column(nullable = false, unique = true)
   private String name  ;
   @Column(nullable = false)
   private String type;

   private LocalDate createdOn ;

   public LocalDate getCreatedOn() {
      return createdOn;
   }

   public void setCreatedOn(LocalDate createdOn) {
      this.createdOn = java.time.LocalDate.now();
   }

   public List<Review> getReviews() {
      return reviews;
   }

   public void setReviews(List<Review> reviews) {
      this.reviews = reviews;
   }



   @OneToMany(mappedBy = "pockemon", cascade = CascadeType.ALL, orphanRemoval = true)
   private List<Review> reviews = new ArrayList<>();

   public UserEntity getUser() {
      return user;
   }

   public void setUser(UserEntity user) {
      this.user = user;
   }

   @ManyToOne(fetch = FetchType.EAGER)
   @JoinColumn(name = "user_id", nullable = false)
   private UserEntity user;

   @Override
   public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      Pockemon pockemon = (Pockemon) o;
      return id == pockemon.id;
   }

   @Override
   public int hashCode() {
      return Objects.hash(id);
   }

   public int getId() {
      return id;
   }

   public void setId(int id) {
      this.id = id;
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public String getType() {
      return type;
   }

   public void setType(String type) {
      this.type = type;
   }
}