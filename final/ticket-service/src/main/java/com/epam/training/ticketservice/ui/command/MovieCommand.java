package com.epam.training.ticketservice.ui.command;

import com.epam.training.ticketservice.core.movie.MovieService;
import com.epam.training.ticketservice.core.movie.model.MovieDto;
import com.epam.training.ticketservice.core.user.LoginService;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;

import java.util.*;


@ShellComponent
public class MovieCommand extends AbstractAuthenticatedCommand {

    private final MovieService movieService;

    public MovieCommand(MovieService movieService, LoginService loginService) {
        super(loginService);
        this.movieService = movieService;
    }

    @ShellMethod(value = "List Movies", key = "list movies")
    public List<MovieDto> listMovies() {
        return movieService.getMovieList();
    }

    @ShellMethodAvailability("admin")
    @ShellMethod(value = "Create Movie", key = "create movie")
    public MovieDto createMovie(String name, String genre, int lengthInMinutes) {
        MovieDto movieDto = new MovieDto(
                name,
                genre,
                lengthInMinutes);
        movieService.createMovie(movieDto);
        return movieDto;
    }

}