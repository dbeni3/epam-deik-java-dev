package com.epam.training.ticketservice.core.movie.persistance.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Column;
import java.util.Objects;

@Entity
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(unique = true)
    private String name;
    private String genre;
    private int lengthInMinutes;

    public Movie(){

    }

    public Movie(Integer id, String name, String genre, int lengthInMinutes) {
        this.id = id;
        this.name = name;
        this.genre = genre;
        this.lengthInMinutes = lengthInMinutes;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getLengthInMinutes() {
        return lengthInMinutes;
    }

    public void setLengthInMinutes(int lengthInMinutes) {
        this.lengthInMinutes = lengthInMinutes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Movie movie = (Movie) o;
        return lengthInMinutes == movie.lengthInMinutes && Objects.equals(id, movie.id)
                && Objects.equals(name, movie.name) && Objects.equals(genre, movie.genre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, genre, lengthInMinutes);
    }

    @Override
    public String toString() {
        return "Movie{" + "id=" + id + ", name='" + name + '\'' + ", genre='" + genre
                + '\'' + ", lengthInMinutes=" + lengthInMinutes + '}';
    }
}
