package com.epam.training.ticketservice.core.movie.impl;

import com.epam.training.ticketservice.core.movie.MovieService;
import com.epam.training.ticketservice.core.movie.model.MovieDto;
import com.epam.training.ticketservice.core.movie.persistance.entity.Movie;
import com.epam.training.ticketservice.core.movie.persistance.repository.MovieRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepository;

    public MovieServiceImpl(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @Override
    public List<MovieDto> getMovieList() {
        return movieRepository.findAll().stream().map(this::convertEntityToDto).collect(Collectors.toList());
    }

    @Override
    public Optional<MovieDto> getMovieByName(String movieName) {
        return convertEntityToDto(movieRepository.findByName(movieName));
    }

    @Override
    public void createMovie(MovieDto movieDto) {
        Objects.requireNonNull(movieDto, "Movie cannot be null");
        Objects.requireNonNull(movieDto.getName(), "Movie Name cannot be null");
        Movie movie = new Movie(null,
                movieDto.getName(),
                movieDto.getGenre(),
                movieDto.getLengthInMin());
        movieRepository.save(movie);
    }

    @Override
    public void updateMovie(MovieDto movieDto) {
        Objects.requireNonNull(movieDto, "Movie cannot be null");
        Objects.requireNonNull(movieDto.getName(), "Movie Name cannot be null");
        Movie movieToChange = movieRepository.findByName(movieDto.getName())
                .orElseThrow(() -> new IllegalArgumentException("The given movie does not exist"));
        movieToChange.setGenre(movieDto.getGenre());
        movieToChange.setLengthInMinutes(movieDto.getLengthInMin());
        movieRepository.save(movieToChange);
    }

    @Override
    public void deleteMovie(String movieName) {
        Objects.requireNonNull(movieName, "Movie name cannot be null");
        movieRepository.deleteByName(movieName);
    }

    private Optional<MovieDto> convertEntityToDto(Optional<Movie> movie) {
        Optional<MovieDto> movieDto;
        if (movie.isEmpty()) {
            movieDto = Optional.empty();
        } else {
            movieDto = Optional.of(convertEntityToDto(movie.get()));
        }
        return movieDto;
    }

    private MovieDto convertEntityToDto(Movie movie) {
        return new MovieDto(movie.getName(), movie.getGenre(), movie.getLengthInMinutes());

    }

}
