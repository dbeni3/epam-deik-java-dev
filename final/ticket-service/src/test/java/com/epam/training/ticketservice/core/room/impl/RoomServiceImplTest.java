package com.epam.training.ticketservice.core.room.impl;


import com.epam.training.ticketservice.core.room.RoomService;
import com.epam.training.ticketservice.core.room.model.RoomDto;
import com.epam.training.ticketservice.core.room.persistance.entity.Room;
import com.epam.training.ticketservice.core.room.persistance.repositpry.RoomRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;


import java.util.List;
import java.util.Optional;

public class RoomServiceImplTest {

    private final String west = "west";
    private final int numberOfRows = 10;
    private final int numberOfColumns = 12;
    private final Room westRoom = new Room(null,west,numberOfRows,numberOfColumns);
    private final Room eastRoom = new Room(null,"east",numberOfRows,numberOfColumns);
    private final RoomDto westDto = new RoomDto(west,numberOfRows,numberOfColumns);
    private final RoomDto eastDto = new RoomDto("east",numberOfRows,numberOfColumns);

    private RoomService underTest;

    private RoomRepository roomRepository;

    @BeforeEach
    public void init() {
        roomRepository = Mockito.mock(RoomRepository.class);
        underTest = new RoomServiceImpl(roomRepository);
    }

    @Test
    public void testGetRoomListShouldCallRoomRepositoryAndReturnADtoList() {
        // Given
        Mockito.when(roomRepository.findAll()).thenReturn(List.of(westRoom,eastRoom));
        List<RoomDto> expected = List.of(westDto,eastDto);

        // When
        List<RoomDto> actual = underTest.getRoomList();

        // Then
        Assertions.assertEquals(expected, actual);
        Mockito.verify(roomRepository).findAll();
        Mockito.verifyNoMoreInteractions(roomRepository);
    }

    @Test
    public void testGetRoomByNameShouldReturnAWestDtoWhenTheRoomExists() {
        // Given
        Mockito.when(roomRepository.findByName(west)).thenReturn(Optional.of(westRoom));
        Optional<RoomDto> expected = Optional.of(westDto);

        // When
        Optional<RoomDto> actual = underTest.getRoomByName(west);

        // Then
        Assertions.assertEquals(expected, actual);
        Mockito.verify(roomRepository).findByName(west);
        Mockito.verifyNoMoreInteractions(roomRepository);
    }
    @Test
    public void testGetRoomByNameShouldReturnOptionalEmptyWhenInputRoomNameDoesNotExist() {
        // Given
        Mockito.when(roomRepository.findByName(west)).thenReturn(Optional.empty());
        Optional<RoomDto> expected = Optional.empty();

        // When
        Optional<RoomDto> actual = underTest.getRoomByName(west);

        // Then
        Assertions.assertTrue(actual.isEmpty());
        Assertions.assertEquals(expected, actual);
        Mockito.verify(roomRepository).findByName(west);
        Mockito.verifyNoMoreInteractions(roomRepository);
    }

    @Test
    public void testGetRoomByNameShouldReturnOptionalEmptyWhenInputRoomNameIsNull() {
        // Given
        Mockito.when(roomRepository.findByName(null)).thenReturn(Optional.empty());
        Optional<RoomDto> expected = Optional.empty();

        // When
        Optional<RoomDto> actual = underTest.getRoomByName(null);

        // Then
        Assertions.assertTrue(actual.isEmpty());
        Assertions.assertEquals(expected, actual);
        Mockito.verify(roomRepository).findByName(null);
        Mockito.verifyNoMoreInteractions(roomRepository);
    }

    @Test
    public void testCreateRoomShouldCallRoomRepositoryWhenTheInputRoomIsValid() {
        // Given
        Mockito.when(roomRepository.save(westRoom)).thenReturn(westRoom);

        // When
        underTest.createRoom(westDto);

        // Then
        Mockito.verify(roomRepository).save(westRoom);
        Mockito.verifyNoMoreInteractions(roomRepository);
    }

    @Test
    public void testCreateRoomShouldThrowNullPointerExceptionWhenRoomIsNull() {
        // Given

        // When
        Assertions.assertThrows(NullPointerException.class, () -> underTest.createRoom(null));

        // Then
        Mockito.verifyNoMoreInteractions(roomRepository);
    }

    @Test
    public void testCreateRoomShouldThrowNullPointerExceptionWhenRoomNameIsNull() {
        // Given
        RoomDto roomDto = new RoomDto(null,10,11);


        // When
        Assertions.assertThrows(NullPointerException.class, () -> underTest.createRoom(roomDto));

        // Then
        Mockito.verifyNoMoreInteractions(roomRepository);
    }


    @Test
    public void testDeleteRoomShouldCallRoomRepositoryWhenTheInputRoomIsValid() {
        // Given
        Mockito.when(roomRepository.save(westRoom)).thenReturn(westRoom);

        // When
        underTest.deleteRoom(west);

        // Then
        Mockito.verify(roomRepository).deleteByName(west);
        Mockito.verifyNoMoreInteractions(roomRepository);
    }

    @Test
    public void testDeleteRoomShouldThrowNullPointerExceptionWhenRoomNameIsNull() {
        // Given


        // When
        Assertions.assertThrows(NullPointerException.class, () -> underTest.deleteRoom(null));

        // Then
        Mockito.verifyNoMoreInteractions(roomRepository);
    }

    @Test
    public void testUpdateRoomShouldCallRoomRepositoryWhenTheInputRoomIsValid(){
        // Given
        Mockito.when(roomRepository.save(westRoom)).thenReturn(westRoom);
        Mockito.when(roomRepository.findByName(west)).thenReturn(Optional.of(westRoom));

        // When
        underTest.updateRoom(westDto);

        // Then
        Mockito.verify(roomRepository).findByName(west);
        Mockito.verify(roomRepository).save(westRoom);
        Mockito.verifyNoMoreInteractions(roomRepository);
    }

    @Test
    public void testUpdateRoomShouldThrowNullPointerExceptionWhenRoomIsNull() {
        // Given


        // When
        Assertions.assertThrows(NullPointerException.class, () -> underTest.updateRoom(null));

        // Then
        Mockito.verifyNoMoreInteractions(roomRepository);
    }

    @Test
    public void testUpdateRoomShouldThrowNullPointerExceptionWhenRoomNameIsNull() {
        // Given
        RoomDto roomDto = new RoomDto(null,10,10);


        // When
        Assertions.assertThrows(NullPointerException.class, () -> underTest.updateRoom(roomDto));

        // Then
        Mockito.verifyNoMoreInteractions(roomRepository);
    }




}
