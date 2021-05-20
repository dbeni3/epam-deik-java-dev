package com.epam.training.ticketservice.core.screening.impl;

import com.epam.training.ticketservice.core.movie.model.MovieDto;
import com.epam.training.ticketservice.core.movie.persistance.entity.Movie;
import com.epam.training.ticketservice.core.movie.persistance.repository.MovieRepository;
import com.epam.training.ticketservice.core.room.RoomService;
import com.epam.training.ticketservice.core.room.impl.RoomServiceImpl;
import com.epam.training.ticketservice.core.room.model.RoomDto;
import com.epam.training.ticketservice.core.room.persistance.entity.Room;
import com.epam.training.ticketservice.core.room.persistance.repositpry.RoomRepository;
import com.epam.training.ticketservice.core.screening.model.ScreeningDto;
import com.epam.training.ticketservice.core.screening.persistance.entity.Screening;
import com.epam.training.ticketservice.core.screening.persistance.repository.ScreeningRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;


import java.util.Date;
import java.util.List;
import java.util.Optional;

public class ScreeningServiceImplTest {

    private final String westName = "west";
    private final String oneName = "one";
    private final Room west = new Room(null, "west" , 10 ,10);
    private final RoomDto westDto = new RoomDto("west",10,10);
    private final Movie one = new Movie(null,"one","d",100);
    private final MovieDto oneDto= new MovieDto("one","d",100);
    private final Screening screening = new Screening(null,one,west,new Date());
    private final ScreeningDto screeningDto = new ScreeningDto(oneDto,westDto,new Date());

    private ScreeningServiceImp underTest;

    private ScreeningRepository screeningRepository;
    private RoomRepository roomRepository;
    private MovieRepository movieRepository;

    @BeforeEach
    void init(){
        screeningRepository = Mockito.mock(ScreeningRepository.class);
        roomRepository = Mockito.mock(RoomRepository.class);
        movieRepository = Mockito.mock(MovieRepository.class);
        underTest = new ScreeningServiceImp(screeningRepository,movieRepository,roomRepository);
    }

    @Test
    public void testGetScreeningListShouldReturnScreeningList(){
        // Given
        Mockito.when(screeningRepository.findAll()).thenReturn(List.of(screening));
        List<ScreeningDto> expected = List.of(screeningDto);

        // When
        List<ScreeningDto> actual = underTest.getScreeningList();

        // Then
        Assertions.assertEquals(expected, actual);
        Mockito.verify(screeningRepository).findAll();
        Mockito.verifyNoMoreInteractions(screeningRepository);
    }

    @Test
    public void testCreateScreeningShouldCallScreeningRepositoryWhenTheInputScreeningIsValid() {
        // Given
        Mockito.when(roomRepository.findByName(westName)).thenReturn(Optional.of(west));
        Mockito.when(movieRepository.findByName(oneName)).thenReturn(Optional.of(one));
        // When

        underTest.createScreening(screeningDto);

        // Then
        Mockito.verify(roomRepository).findByName(westName);
        Mockito.verify(movieRepository).findByName(oneName);
        Mockito.verify(screeningRepository).save(screening);
        Mockito.verify(screeningRepository).findAll();
        Mockito.verifyNoMoreInteractions(screeningRepository);
    }
    @Test

    public void testCreateScreeningShouldCallScreeningRepositoryWhenTheInputScreeningDateIsNotValid() {
        // Given
        Mockito.when(roomRepository.findByName(westName)).thenReturn(Optional.of(west));
        Mockito.when(movieRepository.findByName(oneName)).thenReturn(Optional.of(one));
        Mockito.when(screeningRepository.findAll()).thenReturn(List.of(screening));
        // When

        underTest.createScreening(screeningDto);

        // Then
        Mockito.verify(roomRepository).findByName(westName);
        Mockito.verify(movieRepository).findByName(oneName);
        Mockito.verify(screeningRepository).findAll();
        Mockito.verifyNoMoreInteractions(screeningRepository);
    }


    @Test
    public void testCreateScreeningShouldThrowNullPointerExceptionWhenTheInputScreeningIsNull() {
        // Given

        // When
        Assertions.assertThrows(NullPointerException.class, () -> underTest.createScreening(null));


        // Then
        Mockito.verifyNoMoreInteractions(screeningRepository);
    }

    @Test
    public void testCreateScreeningShouldThrowNullPointerExceptionWhenTheInputScreeningRoomIsNull() {
        // Given
        ScreeningDto screeningDtoWithoutRoom = new ScreeningDto(null,westDto,new Date());
        // When
        Assertions.assertThrows(NullPointerException.class, () -> underTest.createScreening(screeningDtoWithoutRoom));


        // Then
        Mockito.verifyNoMoreInteractions(screeningRepository);
    }

    @Test
    public void testCreateScreeningShouldThrowNullPointerExceptionWhenTheInputScreeningMovieIsNull() {
        // Given
        ScreeningDto screeningDtoWithoutRoom = new ScreeningDto(oneDto,null,new Date());
        // When
        Assertions.assertThrows(NullPointerException.class, () -> underTest.createScreening(screeningDtoWithoutRoom));


        // Then
        Mockito.verifyNoMoreInteractions(screeningRepository);
    }

    @Test
    public void testCreateScreeningShouldThrowNullPointerExceptionWhenTheInputScreeningDateIsNull() {
        // Given
        ScreeningDto screeningDtoWithoutRoom = new ScreeningDto(oneDto,westDto,null);
        // When
        Assertions.assertThrows(NullPointerException.class, () -> underTest.createScreening(screeningDtoWithoutRoom));


        // Then
        Mockito.verifyNoMoreInteractions(screeningRepository);
    }

    @Test
    public void testCreateScreeningShouldThrowIllegalArgumentExceptionWhenTheInputScreeningRoomIsNotExist() {
        // Given
        Mockito.when(movieRepository.findByName(oneName)).thenReturn(Optional.of(one));
        Mockito.when(roomRepository.findByName(westName)).thenReturn(Optional.empty());

        // When
        Assertions.assertThrows(IllegalArgumentException.class, () -> underTest.createScreening(screeningDto));


        // Then
        Mockito.verifyNoMoreInteractions(screeningRepository);
    }

    @Test
    public void testCreateScreeningShouldThrowIllegalArgumentExceptionWhenTheInputScreeningMovieIsNotExist() {
        // Given
        Mockito.when(movieRepository.findByName(oneName)).thenReturn(Optional.of(one));


        // When
        Assertions.assertThrows(IllegalArgumentException.class, () -> underTest.createScreening(screeningDto));


        // Then
        Mockito.verifyNoMoreInteractions(screeningRepository);
    }













}
