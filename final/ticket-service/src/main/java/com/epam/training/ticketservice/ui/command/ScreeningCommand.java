package com.epam.training.ticketservice.ui.command;

import com.epam.training.ticketservice.core.movie.MovieService;
import com.epam.training.ticketservice.core.movie.model.MovieDto;
import com.epam.training.ticketservice.core.room.RoomService;
import com.epam.training.ticketservice.core.room.model.RoomDto;
import com.epam.training.ticketservice.core.screening.ScreeningService;
import com.epam.training.ticketservice.core.screening.model.ScreeningDto;
import com.epam.training.ticketservice.core.user.LoginService;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;

import java.io.Console;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

@ShellComponent
public class ScreeningCommand extends AbstractAuthenticatedCommand {

    private final ScreeningService screeningService;
    private final MovieService movieService;
    private final RoomService roomService;

    public ScreeningCommand(LoginService loginService, ScreeningService screeningService, MovieService movieService, RoomService roomService) {
        super(loginService);
        this.screeningService = screeningService;
        this.movieService = movieService;
        this.roomService = roomService;
    }

    //Még le kell kezelni ha nem létezik az adatbázisban az input movie or room
    @ShellMethodAvailability("admin")
    @ShellMethod(value = "create Screening", key = "create screening")
    public ScreeningDto createScreening(String movieName, String roomName, String sDate) {
        Optional<MovieDto> optionalMovie = movieService.getMovieByName(movieName);

        Optional<RoomDto> optionalRoom = roomService.getRoomByName(roomName);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        Date date = new Date();
        try {
            date = formatter.parse(sDate);
        } catch (ParseException exception) {
            System.out.println("Time format is yyyy-MM-dd hh:mm");
        }
        ScreeningDto screeningDto = new ScreeningDto(optionalMovie.get(),optionalRoom.get(),date);

        screeningService.createScreening(screeningDto);
        return screeningDto;
    }

}
