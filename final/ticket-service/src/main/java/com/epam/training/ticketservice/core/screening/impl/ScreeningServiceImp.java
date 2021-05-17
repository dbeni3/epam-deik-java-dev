package com.epam.training.ticketservice.core.screening.impl;


import com.epam.training.ticketservice.core.movie.model.MovieDto;
import com.epam.training.ticketservice.core.movie.persistance.entity.Movie;
import com.epam.training.ticketservice.core.movie.persistance.repository.MovieRepository;
import com.epam.training.ticketservice.core.room.model.RoomDto;
import com.epam.training.ticketservice.core.room.persistance.entity.Room;
import com.epam.training.ticketservice.core.room.persistance.repositpry.RoomRepository;
import com.epam.training.ticketservice.core.screening.ScreeningService;
import com.epam.training.ticketservice.core.screening.model.ScreeningDto;
import com.epam.training.ticketservice.core.screening.persistance.entity.Screening;
import com.epam.training.ticketservice.core.screening.persistance.repository.ScreeningRepository;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class ScreeningServiceImp implements ScreeningService {

    private final ScreeningRepository screeningRepository;
    private final MovieRepository movieRepository;
    private final RoomRepository roomRepository;

    public ScreeningServiceImp(ScreeningRepository screeningRepository, MovieRepository movieRepository, RoomRepository roomRepository) {
        this.screeningRepository = screeningRepository;
        this.movieRepository = movieRepository;
        this.roomRepository = roomRepository;
    }

    @Override
    public void createScreening(ScreeningDto screeningDto){
        Objects.requireNonNull(screeningDto, "ScreeningDto cannot be null");
        Objects.requireNonNull(screeningDto.getMovie(), "Movie cannot be null");
        Objects.requireNonNull(screeningDto.getRoom(), "Room cannot be null");
        Objects.requireNonNull(screeningDto.getDate(), "Date cannot be null");
        Movie movie = movieRepository.findByName(screeningDto.getMovie().getName())
                .orElseThrow(() -> new IllegalArgumentException("The given movie does not exist"));;
        Room room = roomRepository.findByName(screeningDto.getRoom().getName())
                .orElseThrow(() -> new IllegalArgumentException("The given room does not exist"));
        Screening screening=new Screening(null,movie,room,screeningDto.getDate());
        screeningRepository.save(screening);
    }

}
