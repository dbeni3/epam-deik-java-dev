package com.epam.training.ticketservice.core.configuration;


import com.epam.training.ticketservice.core.user.persistence.entity.User;
import com.epam.training.ticketservice.core.user.persistence.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;



public class InMemoryDbInitializerTest {

    private InMemoryDbInitializer underTest;

    private UserRepository userRepository;

    @BeforeEach
    public void init() {
        userRepository = Mockito.mock(UserRepository.class);
        underTest = new InMemoryDbInitializer(userRepository);
    }

    @Test
    public void testInitShouldCallUserRepository(){
        // Given

        // When

        underTest.init();

        // Then
        Mockito.verify(userRepository).save( new User(null, "admin", "admin", User.Role.ADMIN));
        Mockito.verifyNoMoreInteractions(userRepository);
    }
}
