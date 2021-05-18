package com.epam.training.ticketservice.core.room.impl;

import com.epam.training.ticketservice.core.movie.model.MovieDto;
import com.epam.training.ticketservice.core.movie.persistance.entity.Movie;
import com.epam.training.ticketservice.core.room.RoomService;
import com.epam.training.ticketservice.core.room.model.RoomDto;
import com.epam.training.ticketservice.core.room.persistance.entity.Room;
import com.epam.training.ticketservice.core.room.persistance.repositpry.RoomRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class RoomServiceImpl implements RoomService {

    private final RoomRepository roomRepository;

    public RoomServiceImpl(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }
    @Override
    public List<RoomDto> getRoomList(){
        return roomRepository.findAll().stream().map(this::convertEntityToDto).collect(Collectors.toList());
    }
    @Override
    public Optional<RoomDto> getRoomByName(String roomName) {
        return convertEntityToDto(roomRepository.findByName(roomName));
    }
    @Override
    public void createRoom(RoomDto roomDto) {
        Objects.requireNonNull(roomDto, "Room cannot be null");
        Objects.requireNonNull(roomDto.getName(), "Room Name cannot be null");
        Room room = new Room(null,
                roomDto.getName(),
                roomDto.getNumbersOfRows(),
                roomDto.getNumbersOfColumns());

        roomRepository.save(room);
    }
    @Override
    public void updateRoom(RoomDto roomDto){
        Objects.requireNonNull(roomDto, "Room cannot be null");
        Objects.requireNonNull(roomDto.getName(), "Room Name cannot be null");
        Room roomToChange = roomRepository.findByName(roomDto.getName()).get();
        roomToChange.setNumberOfColumns(roomDto.getNumbersOfColumns());
        roomToChange.setNumberOfRows(roomDto.getNumbersOfRows());
        roomRepository.save(roomToChange);
    }

    @Override
    public void deleteRoom(String roomName){
        Objects.requireNonNull(roomName, "Room name cannot be null");
        roomRepository.deleteByName(roomName);
    }

    private Optional<RoomDto> convertEntityToDto(Optional<Room> room) {
        Optional<RoomDto> roomDto;
        if(room.isEmpty()) {
            roomDto = Optional.empty();
        } else {
            roomDto = Optional.of(convertEntityToDto(room.get()));
        }
        return roomDto;
    }


    private RoomDto convertEntityToDto(Room room) {
        return new RoomDto(room.getName(), room.getNumberOfRows(), room.getNumberOfColumns());

    }
}
