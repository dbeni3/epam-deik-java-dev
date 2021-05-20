package com.epam.training.ticketservice.core.movie.persistance.entity;

import com.epam.training.ticketservice.core.user.persistence.entity.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MovieTest {

    private Movie underTest;
    private final String name = "name";
    private final String drama = "drama";
    private final int length = 100;

    @BeforeEach
    public void init() {
        underTest = new Movie(1,name,drama, length);
    }

    @Test
    public void testGetIdShouldReturnId(){
        //Given

        int expected = 1;

        //When
        int actual = underTest.getId();

        //Then
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void testGetNameShouldReturnId(){
        //Given

        String expected = name;

        //When
        String actual = underTest.getName();

        //Then
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void testSetNameShouldChangeName(){
        //Given

        String expected = "one";

        //When
        underTest.setName("one");
        String actual = underTest.getName();

        //Then
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void testGetGenreShouldReturnGenre(){
        //Given

        String expected = drama;

        //When
        String actual = underTest.getGenre();

        //Then
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void testSetGenreShouldChangeGenre(){
        //Given

        String expected = "thriller";

        //When
        underTest.setGenre("thriller");
        String actual = underTest.getGenre();

        //Then
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void testGetLengthInMinutesShouldReturnLengthInMinutes(){
        //Given

        String expected = drama;

        //When
        String actual = underTest.getGenre();

        //Then
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void testSetLengthInMinutesShouldChangeLengthInMinutes(){
        //Given

        int expected = 1;

        //When
        underTest.setLengthInMinutes(1);
        int actual = underTest.getLengthInMinutes();

        //Then
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void testToStringShouldReturn() {
        //Given

        String expected = "Movie{" + "id=" + underTest.getId() + ", name='" + underTest.getName()
                + '\'' + ", genre='" + underTest.getGenre()
                + '\'' + ", lengthInMinutes=" + underTest.getLengthInMinutes() + '}';

        //When
        String actual = underTest.toString();

        //Then
        Assertions.assertEquals(expected, actual);
    }


}
