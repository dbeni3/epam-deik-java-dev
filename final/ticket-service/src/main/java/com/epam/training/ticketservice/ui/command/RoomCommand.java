package com.epam.training.ticketservice.ui.command;

import com.epam.training.ticketservice.core.movie.model.MovieDto;
import com.epam.training.ticketservice.core.room.RoomService;
import com.epam.training.ticketservice.core.room.model.RoomDto;
import com.epam.training.ticketservice.core.user.LoginService;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;

import java.util.List;

@ShellComponent
public class RoomCommand extends AbstractAuthenticatedCommand{

    private final RoomService roomService;

    public RoomCommand(LoginService loginService, RoomService roomService) {
        super(loginService);
        this.roomService = roomService;
    }

    @ShellMethod(value = "List Rooms", key = "list rooms")
    public void listRooms() {
        if (roomService.getRoomList().isEmpty()) {
            System.out.println("There are no rooms at the moment");
        }else {
            roomService.getRoomList()
                    .forEach((m) ->
                            System.out.println( "Room " +
                                    m.getName() + " with " +
                                    m.getNumbersOfColumns()*m.getNumbersOfRows() + " seats, " +
                                    m.getNumbersOfRows() + " rows and " +
                                    m.getNumbersOfColumns() + " columns"
                            ));
        }
    }

    @ShellMethodAvailability("admin")
    @ShellMethod(value = "create Room", key = "create room")
    public RoomDto createRoom(String name, int numberOfRows, int numberOfColumns) {
        RoomDto roomDto = new RoomDto(name,numberOfRows,numberOfColumns);
        roomService.createRoom(roomDto);
        return roomDto;
    }
    @ShellMethodAvailability("admin")
    @ShellMethod(value = "Update Room", key = "update room")
    public void updateMovie(String name, int numberOfRows, int numberOfColumns) {
        RoomDto roomDto = new RoomDto(
                name,
                numberOfRows,
                numberOfColumns);
        if (roomService.getRoomByName(roomDto.getName()).isEmpty()){
            System.out.println("Room does not exist");
        }else{
            roomService.updateRoom(roomDto);
        }
    }

    @ShellMethodAvailability("admin")
    @ShellMethod(value = "Delete Room", key = "delete room")
    public void deleteMovie(String name) {
        if (roomService.getRoomByName(name).isEmpty()){
            System.out.println("Room does not exist");
        }else{
            roomService.deleteRoom(name);
        }
    }
}
