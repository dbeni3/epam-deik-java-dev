package com.epam.training.ticketservice.core.movie.impl;

import com.epam.training.ticketservice.core.movie.model.MovieDto;
import com.epam.training.ticketservice.core.movie.persistance.entity.Movie;
import com.epam.training.ticketservice.core.movie.persistance.repository.MovieRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Currency;
import java.util.List;
import java.util.Optional;

public class MovieServiceImplTest {

    private final String one = "one";
    private final String drama = "drama";
    private final int movieLenght = 100;
    private final Movie oneMovie = new Movie(null,one,drama,movieLenght);
    private final Movie twoMovie = new Movie(null,"two","drama",101);

    private final MovieDto oneDto = new MovieDto(one,drama,movieLenght);
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
    public void testGetMovieByNameShouldReturnAOneDtoWhenTheProductExists() {
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
    public void testGetProductByNameShouldReturnOptionalEmptyWhenInputProductNameDoesNotExist() {
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
    public void testGetProductByNameShouldReturnOptionalEmptyWhenInputProductNameIsNull() {
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
    public void testCreateProductShouldCallProductRepositoryWhenTheInputProductIsValid() {
        // Given
        Mockito.when(movieRepository.save(oneMovie)).thenReturn(oneMovie);

        // When
        underTest.createMovie(oneDto);

        // Then
        Mockito.verify(movieRepository).save(oneMovie);
        Mockito.verifyNoMoreInteractions(movieRepository);
    }

    @Test
    public void testCreateProductShouldThrowNullPointerExceptionWhenProductIsNull() {
        // Given

        // When
        Assertions.assertThrows(NullPointerException.class, () -> underTest.createMovie(null));

        // Then
        Mockito.verifyNoMoreInteractions(movieRepository);
    }

    @Test
    public void testCreateProductShouldThrowNullPointerExceptionWhenProductNameIsNull() {
        // Given
        MovieDto movieDto = new MovieDto(null,"d",100);


        // When
        Assertions.assertThrows(NullPointerException.class, () -> underTest.createMovie(movieDto));

        // Then
        Mockito.verifyNoMoreInteractions(movieRepository);
    }

    //UPDATEMOVIE
    //DELETEMOVIE


}
