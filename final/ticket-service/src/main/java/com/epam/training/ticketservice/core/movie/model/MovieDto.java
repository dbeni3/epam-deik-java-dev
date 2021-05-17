package com.epam.training.ticketservice.core.movie.model;

import java.util.Objects;

public class MovieDto {

    private final String name;
    private final String genre;
    private final int lengthInMin;

    public MovieDto(String name, String genre, int lengthInMin) {
        this.name = name;
        this.genre = genre;
        this.lengthInMin = lengthInMin;
    }

    public String getName() { return name; }

    public String getGenre() { return genre; }

    public int getLengthInMin() { return lengthInMin; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MovieDto movieDto = (MovieDto) o;
        return Double.compare(movieDto.lengthInMin, lengthInMin) == 0 && Objects.equals(name, movieDto.name) && Objects.equals(genre, movieDto.genre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, genre, lengthInMin);
    }

    @Override
    public String toString() {
        return "MovieDto{" +
                "name='" + name + '\'' +
                ", genre='" + genre + '\'' +
                ", lengthInMin=" + lengthInMin +
                '}';
    }
}
