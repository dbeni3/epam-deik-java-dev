package com.epam.training.ticketservice.core.user.impl;

import com.epam.training.ticketservice.core.user.model.UserDto;
import com.epam.training.ticketservice.core.user.persistence.entity.User;
import com.epam.training.ticketservice.core.user.persistence.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

public class UserServiceImplTest {

    private UserServiceImpl underTest;

    private UserRepository userRepository;

    @BeforeEach
    public void init() {
        userRepository = Mockito.mock(UserRepository.class);
        underTest = new UserServiceImpl(userRepository);
    }

    @Test
    public void testRetrieveUserByUsernameAndPasswordShouldThrowNullPointerExceptionWhenUsernameIsNull(){
        // Given

        // When
        Assertions.assertThrows(NullPointerException.class, () -> underTest.retrieveUserByUsernameAndPassword(null, "pw"));

        // Then
        Mockito.verifyNoMoreInteractions(userRepository);
    }

    @Test
    public void testRetrieveUserByUsernameAndPasswordShouldThrowNullPointerExceptionWhenPasswordIsNull(){
        // Given

        // When
        Assertions.assertThrows(NullPointerException.class, () -> underTest.retrieveUserByUsernameAndPassword("admin", null));

        // Then
        Mockito.verifyNoMoreInteractions(userRepository);
    }

    @Test
    public void testRetrieveUserByUsernameAndPasswordShouldReturnAUserDtoWhenTheInputsAreValidAndTheUserExists() {
        // Given
        Optional<User> user = Optional.of(new User(1, "admin", "admin", User.Role.ADMIN));
        Mockito.when(userRepository.findByUsernameAndPassword("admin", "admin")).thenReturn(user);
        Optional<UserDto> expected = Optional.of(new UserDto("admin", User.Role.ADMIN));

        // When
        Optional<UserDto> actual = underTest.retrieveUserByUsernameAndPassword("admin", "admin");

        // Then
        Assertions.assertEquals(expected, actual);
        Mockito.verify(userRepository).findByUsernameAndPassword("admin", "admin");
        Mockito.verifyNoMoreInteractions(userRepository);
    }

    @Test
    public void testRetrieveUserByUsernameAndPasswordShouldReturnAnOptionalEmptyWhenTheInputsAreValidButTheUserDoesNotExist() {
        // Given
        Mockito.when(userRepository.findByUsernameAndPassword("admin", "admin")).thenReturn(Optional.empty());
        Optional<UserDto> expected = Optional.empty();

        // When
        Optional<UserDto> actual = underTest.retrieveUserByUsernameAndPassword("admin", "admin");

        // Then
        Assertions.assertEquals(expected, actual);
        Mockito.verify(userRepository).findByUsernameAndPassword("admin", "admin");
        Mockito.verifyNoMoreInteractions(userRepository);
    }




}