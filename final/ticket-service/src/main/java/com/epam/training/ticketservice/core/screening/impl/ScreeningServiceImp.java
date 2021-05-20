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
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ScreeningServiceImp implements ScreeningService {

    private final ScreeningRepository screeningRepository;
    private final MovieRepository movieRepository;
    private final RoomRepository roomRepository;

    public ScreeningServiceImp(ScreeningRepository screeningRepository,
                               MovieRepository movieRepository, RoomRepository roomRepository) {
        this.screeningRepository = screeningRepository;
        this.movieRepository = movieRepository;
        this.roomRepository = roomRepository;
    }

    @Override
    public List<ScreeningDto> getScreeningList() {
        return screeningRepository.findAll().stream().map(this::convertEntityToDto).collect(Collectors.toList());
    }

    @Override
    public void createScreening(ScreeningDto screeningDto) {
        Objects.requireNonNull(screeningDto, "ScreeningDto cannot be null");
        Objects.requireNonNull(screeningDto.getMovie(), "Movie cannot be null");
        Objects.requireNonNull(screeningDto.getRoom(), "Room cannot be null");
        Objects.requireNonNull(screeningDto.getDate(), "Date cannot be null");
        Movie movie = movieRepository.findByName(screeningDto.getMovie().getName())
                .orElseThrow(() -> new IllegalArgumentException("The given movie does not exist"));
        Room room = roomRepository.findByName(screeningDto.getRoom().getName())
                .orElseThrow(() -> new IllegalArgumentException("The given room does not exist"));
        Screening screening = new Screening(null,movie,room,screeningDto.getDate());
        if (isRoomEmpty(screening)) {
            screeningRepository.save(screening);
            System.out.println("Screening successfully created!");
        }
    }


    private boolean isRoomEmpty(Screening screening) {
        List<ScreeningDto> screeningList = screeningRepository
                .findAll().stream().map(this::convertEntityToDto)
                .filter(sc -> sc.getRoom().getName().equals(screening.getRoom().getName()))
                .collect(Collectors.toList());
        for (ScreeningDto screeningDto : screeningList) {
            int movieLength = screeningDto.getMovie().getLengthInMin();
            Date screeningBegin = screeningDto.getDate();
            Date screeningEnd = screeningBegin;
            Date screeningEndAndBreak = screeningBegin;
            screeningEnd = DateUtils.addMinutes(screeningEnd, movieLength);
            screeningEndAndBreak = DateUtils.addMinutes(screeningEndAndBreak, movieLength + 10);
            if (screeningBegin.compareTo(screening.getDate()) <= 0 && screening.getDate().compareTo(screeningEnd) < 0) {
                System.out.println("There is an overlapping screening");
                return false;
            }
            if (screeningBegin.compareTo(screening.getDate()) <= 0
                    && screening.getDate().compareTo(screeningEndAndBreak) <= 0) {
                System.out.println("This would start in the break period after another screening in this room");
                return false;
            }
        }
        return true;
    }

    private Optional<ScreeningDto> convertEntityToDto(Optional<Screening> screening) {
        Optional<ScreeningDto> screeningDto;
        if (screening.isEmpty()) {
            screeningDto = Optional.empty();
        } else {
            screeningDto = Optional.of(convertEntityToDto(screening.get()));
        }
        return screeningDto;
    }


    private ScreeningDto convertEntityToDto(Screening screening) {
        return new ScreeningDto(convertMovieEntityToDto(screening.getMovie()),
                convertRoomEntityToDto(screening.getRoom()), screening.getDate());
    }

    private Optional<MovieDto> convertMovieEntityToDto(Optional<Movie> movie) {
        Optional<MovieDto> movieDto;
        if (movie.isEmpty()) {
            movieDto = Optional.empty();
        } else {
            movieDto = Optional.of(convertMovieEntityToDto(movie.get()));
        }
        return movieDto;
    }

    private MovieDto convertMovieEntityToDto(Movie movie) {
        return new MovieDto(movie.getName(), movie.getGenre(), movie.getLengthInMinutes());

    }

    private Optional<RoomDto> convertRoomEntityToDto(Optional<Room> room) {
        Optional<RoomDto> roomDto;
        if (room.isEmpty()) {
            roomDto = Optional.empty();
        } else {
            roomDto = Optional.of(convertRoomEntityToDto(room.get()));
        }
        return roomDto;
    }


    private RoomDto convertRoomEntityToDto(Room room) {
        return new RoomDto(room.getName(), room.getNumberOfRows(), room.getNumberOfColumns());
    }


}
