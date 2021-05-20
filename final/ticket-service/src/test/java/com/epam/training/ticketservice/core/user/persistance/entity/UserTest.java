package com.epam.training.ticketservice.core.user.persistance.entity;

import com.epam.training.ticketservice.core.user.persistence.entity.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class UserTest {

    private User underTest;
    private final String name = "name";
    private final String pass = "pass";
    @BeforeEach
    public void init() {
        underTest = new User(1,name,pass, User.Role.ADMIN);
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
    public void testGetUserNameShouldReturnName(){
        //Given

        String expected = name;

        //When
        String actual = underTest.getUsername();

        //Then
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void testSetUserNameShouldChangeName(){
        //Given

        String expected = "Admin";

        //When
        underTest.setUsername("Admin");
        String actual = underTest.getUsername();

        //Then
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void testGetPasswordShouldReturnPassword(){
        //Given

        String expected = pass;

        //When
        String actual = underTest.getPassword();

        //Then
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void testSetPasswordShouldChangePassword(){
        //Given

        String expected = "Admin";

        //When
        underTest.setPassword("Admin");
        String actual = underTest.getPassword();

        //Then
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void testToStringShouldReturn(){
        //Given

        String expected ="User{" + "id=" + underTest.getId() + ", username='" + underTest.getUsername()
                + '\'' + ", password='" + underTest.getPassword() + '\'' + ", role=" + underTest.getRole() + '}';

        //When
        String actual = underTest.toString();

        //Then
        Assertions.assertEquals(expected, actual);
    }



}
