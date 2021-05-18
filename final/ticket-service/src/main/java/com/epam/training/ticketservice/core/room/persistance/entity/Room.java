package com.epam.training.ticketservice.core.room.persistance.entity;


import javax.persistence.*;
import java.util.Objects;

@Entity
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(unique = true)
    private String name;
    private int numberOfRows;
    private int numberOfColumns;

    public Room(){

    }

    public Room(Integer id, String name, int numberOfRows, int numberOfColumns) {
        this.id = id;
        this.name = name;
        this.numberOfRows = numberOfRows;
        this.numberOfColumns = numberOfColumns;
    }

    public Integer getId() {return id;}

    public void setId(Integer id) {this.id = id; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public int getNumberOfRows() { return numberOfRows; }

    public void setNumberOfRows(int numberOfRows) {this.numberOfRows = numberOfRows; }

    public int getNumberOfColumns() {return numberOfColumns;  }

    public void setNumberOfColumns(int numberOfColumns) { this.numberOfColumns = numberOfColumns; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Room room = (Room) o;
        return numberOfRows == room.numberOfRows && numberOfColumns == room.numberOfColumns
                && Objects.equals(id, room.id) && Objects.equals(name, room.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, numberOfRows, numberOfColumns);
    }

    @Override
    public String toString() {
        return "Room{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", numberOfRows=" + numberOfRows +
                ", numberOfColumns=" + numberOfColumns +
                '}';
    }
}
