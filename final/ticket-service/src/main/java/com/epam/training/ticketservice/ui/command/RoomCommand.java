package com.epam.training.ticketservice.ui.command;

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
    public List<RoomDto> listRooms() {
        return roomService.getRoomList();
    }

    @ShellMethodAvailability("admin")
    @ShellMethod(value = "create Room", key = "create room")
    public RoomDto createRoom(String name, int numberOfRows, int numberOfColumns) {
        RoomDto roomDto = new RoomDto(name,numberOfRows,numberOfColumns);
        roomService.createRoom(roomDto);
        return roomDto;
    }
}
