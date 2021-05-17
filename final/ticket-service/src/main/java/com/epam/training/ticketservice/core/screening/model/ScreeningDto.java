package com.epam.training.ticketservice.core.screening.model;

import com.epam.training.ticketservice.core.movie.model.MovieDto;
import com.epam.training.ticketservice.core.movie.persistance.entity.Movie;
import com.epam.training.ticketservice.core.room.model.RoomDto;
import com.epam.training.ticketservice.core.room.persistance.entity.Room;

import java.util.Date;
import java.util.Objects;

public class ScreeningDto {

    private final MovieDto movie;
    private final RoomDto room;
    private final Date date;

    public ScreeningDto(MovieDto movie, RoomDto room, Date date) {
        this.movie = movie;
        this.room = room;
        this.date = date;
    }

    public MovieDto getMovie() {
        return movie;
    }

    public RoomDto getRoom() {
        return room;
    }

    public Date getDate() {
        return date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ScreeningDto that = (ScreeningDto) o;
        return Objects.equals(movie, that.movie) && Objects.equals(room, that.room) && Objects.equals(date, that.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(movie, room, date);
    }

    @Override
    public String toString() {
        return "ScreeningDto{" +
                "movie=" + movie +
                ", room=" + room +
                ", date=" + date +
                '}';
    }
}
