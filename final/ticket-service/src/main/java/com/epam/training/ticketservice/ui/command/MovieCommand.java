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
    public void listMovies() {
        if ( movieService.getMovieList().isEmpty()){
            System.out.println("There are no movies at the moment");
        }else {
            movieService.getMovieList()
                    .forEach((m) ->
                            System.out.println(
                                    m.getName() + " (" + m.getGenre() + ", " + m.getLengthInMin() + " minutes)"));
        }
    }

    @ShellMethodAvailability("admin")
    @ShellMethod(value = "Create Movie", key = "create movie")
    public MovieDto createMovie(String name, String genre, int lengthInMinutes) {
        MovieDto movieDto = new MovieDto(name,genre,lengthInMinutes);
        movieService.createMovie(movieDto);
        return movieDto;
    }

    @ShellMethodAvailability("admin")
    @ShellMethod(value = "Update Movie", key = "update movie")
    public void updateMovie(String name, String genre, int lengthInMinutes) {
        MovieDto movieDto = new MovieDto(
                name,
                genre,
                lengthInMinutes);
        if (movieService.getMovieByName(movieDto.getName()).isEmpty()){
            System.out.println("Movie does not exist");
        }else{
            movieService.updateMovie(movieDto);
        }
    }

    @ShellMethodAvailability("admin")
    @ShellMethod(value = "Delete Movie", key = "delete movie")
    public void deleteMovie(String name) {
        if (movieService.getMovieByName(name).isEmpty()){
            System.out.println("Movie does not exist");
        }else{
            movieService.deleteMovie(name);
        }
    }

}