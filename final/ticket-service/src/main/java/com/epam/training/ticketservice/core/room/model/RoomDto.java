package com.epam.training.ticketservice.core.room.model;

import java.util.Objects;

public class RoomDto {

    private final String name;
    private final int numbersOfRows;
    private final int numbersOfColumns;

    public RoomDto(String name, int numbersOfRows, int numbersOfColumns) {
        this.name = name;
        this.numbersOfRows = numbersOfRows;
        this.numbersOfColumns = numbersOfColumns;
    }

    public String getName() {
        return name;
    }

    public int getNumbersOfRows() {
        return numbersOfRows;
    }

    public int getNumbersOfColumns() {
        return numbersOfColumns;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RoomDto roomDto = (RoomDto) o;
        return numbersOfRows == roomDto.numbersOfRows && numbersOfColumns == roomDto.numbersOfColumns && Objects.equals(name, roomDto.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, numbersOfRows, numbersOfColumns);
    }

    @Override
    public String toString() {
        return "RoomDto{" +
                "name='" + name + '\'' +
                ", numbersOfRows=" + numbersOfRows +
                ", numbersOfColumns=" + numbersOfColumns +
                '}';
    }
}
