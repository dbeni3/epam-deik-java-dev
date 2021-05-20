package com.epam.training.ticketservice.core.movie.impl;

import com.epam.training.ticketservice.core.movie.model.MovieDto;
import com.epam.training.ticketservice.core.movie.persistance.entity.Movie;
import com.epam.training.ticketservice.core.movie.persistance.repository.MovieRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;


import java.util.List;
import java.util.Optional;

public class MovieServiceImplTest {

    private final String one = "one";
    private final String drama = "drama";
    private final int movieLength = 100;
    private final Movie oneMovie = new Movie(null,one,drama, movieLength);
    private final Movie twoMovie = new Movie(null,"two","drama",101);

    private final MovieDto oneDto = new MovieDto(one,drama, movieLength);
    private final MovieDto twoDto = new MovieDto("two","drama",101);

    private MovieServiceImpl underTest;

    private MovieRepository movieRepository;

    @BeforeEach
    public void init() {
        movieRepository = Mockito.mock(MovieRepository.class);
        underTest = new MovieServiceImpl(movieRepository);
    }

    @Test
    public void testGetMovieListShouldCallMovieRepositoryAndReturnADtoList() {
        // Given
        Mockito.when(movieRepository.findAll()).thenReturn(List.of(oneMovie,twoMovie));
        List<MovieDto> expected = List.of(oneDto,twoDto);

        // When
        List<MovieDto> actual = underTest.getMovieList();

        // Then
        Assertions.assertEquals(expected, actual);
        Mockito.verify(movieRepository).findAll();
        Mockito.verifyNoMoreInteractions(movieRepository);
    }

    @Test
    public void testGetMovieByNameShouldReturnAOneDtoWhenTheMovieExists() {
        // Given
        Mockito.when(movieRepository.findByName(one)).thenReturn(Optional.of(oneMovie));
        Optional<MovieDto> expected = Optional.of(oneDto);

        // When
        Optional<MovieDto> actual = underTest.getMovieByName(one);

        // Then
        Assertions.assertEquals(expected, actual);
        Mockito.verify(movieRepository).findByName(one);
        Mockito.verifyNoMoreInteractions(movieRepository);
    }
    @Test
    public void testGetMovieByNameShouldReturnOptionalEmptyWhenInputMovieNameDoesNotExist() {
        // Given
        Mockito.when(movieRepository.findByName(one)).thenReturn(Optional.empty());
        Optional<MovieDto> expected = Optional.empty();

        // When
        Optional<MovieDto> actual = underTest.getMovieByName(one);

        // Then
        Assertions.assertTrue(actual.isEmpty());
        Assertions.assertEquals(expected, actual);
        Mockito.verify(movieRepository).findByName(one);
        Mockito.verifyNoMoreInteractions(movieRepository);
    }

    @Test
    public void testGetMovieByNameShouldReturnOptionalEmptyWhenInputMovieNameIsNull() {
        // Given
        Mockito.when(movieRepository.findByName(null)).thenReturn(Optional.empty());
        Optional<MovieDto> expected = Optional.empty();

        // When
        Optional<MovieDto> actual = underTest.getMovieByName(null);

        // Then
        Assertions.assertTrue(actual.isEmpty());
        Assertions.assertEquals(expected, actual);
        Mockito.verify(movieRepository).findByName(null);
        Mockito.verifyNoMoreInteractions(movieRepository);
    }

    @Test
    public void testCreateMovieShouldCallMovieRepositoryWhenTheInputMovieIsValid() {
        // Given
        Mockito.when(movieRepository.save(oneMovie)).thenReturn(oneMovie);

        // When
        underTest.createMovie(oneDto);

        // Then
        Mockito.verify(movieRepository).save(oneMovie);
        Mockito.verifyNoMoreInteractions(movieRepository);
    }

    @Test
    public void testCreateMovieShouldThrowNullPointerExceptionWhenMovieIsNull() {
        // Given

        // When
        Assertions.assertThrows(NullPointerException.class, () -> underTest.createMovie(null));

        // Then
        Mockito.verifyNoMoreInteractions(movieRepository);
    }

    @Test
    public void testCreateMovieShouldThrowNullPointerExceptionWhenMovieNameIsNull() {
        // Given
        MovieDto movieDto = new MovieDto(null,"d",100);


        // When
        Assertions.assertThrows(NullPointerException.class, () -> underTest.createMovie(movieDto));

        // Then
        Mockito.verifyNoMoreInteractions(movieRepository);
    }


    @Test
    public void testDeleteMovieShouldCallMovieRepositoryWhenTheInputMovieIsValid() {
        // Given
        Mockito.when(movieRepository.save(oneMovie)).thenReturn(oneMovie);

        // When
        underTest.deleteMovie(one);

        // Then
        Mockito.verify(movieRepository).deleteByName(one);
        Mockito.verifyNoMoreInteractions(movieRepository);
    }

    @Test
    public void testDeleteMovieShouldThrowNullPointerExceptionWhenMovieNameIsNull() {
        // Given


        // When
        Assertions.assertThrows(NullPointerException.class, () -> underTest.deleteMovie(null));

        // Then
        Mockito.verifyNoMoreInteractions(movieRepository);
    }


    @Test
    public void testUpdateMovieShouldCallMovieRepositoryWhenTheInputMovieIsValid(){
        // Given
        Mockito.when(movieRepository.save(oneMovie)).thenReturn(oneMovie);
        Mockito.when(movieRepository.findByName(one)).thenReturn(Optional.of(oneMovie));

        // When
        underTest.updateMovie(oneDto);

        // Then
        Mockito.verify(movieRepository).findByName(one);
        Mockito.verify(movieRepository).save(oneMovie);
        Mockito.verifyNoMoreInteractions(movieRepository);
    }

    @Test
    public void testUpdateMovieShouldThrowNullPointerExceptionWhenMovieIsNull() {
        // Given


        // When
        Assertions.assertThrows(NullPointerException.class, () -> underTest.updateMovie(null));

        // Then
        Mockito.verifyNoMoreInteractions(movieRepository);
    }

    @Test
    public void testUpdateMovieShouldThrowNullPointerExceptionWhenMovieNameIsNull() {
        // Given
        MovieDto movieDto = new MovieDto(null,"d",100);

        // When
        Assertions.assertThrows(NullPointerException.class, () -> underTest.updateMovie(movieDto));

        // Then
        Mockito.verifyNoMoreInteractions(movieRepository);
    }


}
