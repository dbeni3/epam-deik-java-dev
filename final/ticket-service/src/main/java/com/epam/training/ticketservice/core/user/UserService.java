package com.epam.training.ticketservice.core.user;

import com.epam.training.ticketservice.core.user.model.UserDto;

import java.util.Optional;

public interface UserService {

    Optional<UserDto> retrieveUserByUsernameAndPassword(String username, String password);
}
