package com.epam.training.ticketservice.ui.command;

import com.epam.training.ticketservice.core.user.LoginService;
import com.epam.training.ticketservice.core.user.UserService;
import com.epam.training.ticketservice.core.user.model.UserDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import java.util.Optional;
import java.util.function.Function;
import java.util.function.Supplier;

@ShellComponent
public class UserCommand {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserCommand.class);

    private final LoginService loginService;
    private final UserService userService;

    public UserCommand(LoginService loginService, UserService userService) {
        this.loginService = loginService;
        this.userService = userService;
    }

    @ShellMethod(value = "Sign in privileged", key = "sign in privileged")
    public String login(String username, String password) {
        return handleErrorScenario(() -> loginService.login(username, password),
                (userDto) -> userDto.toString(),
                "Wrong username or password");
    }

    @ShellMethod(value = "sign out", key = "sign out")
    public String logout() {
        return handleErrorScenario(() -> loginService.logout(),
                (userDto) -> userDto.getUsername() + " is logged out",
                "You need to login first");
    }

    private String handleErrorScenario(Supplier<Optional<UserDto>> supplier, Function<UserDto, String> successfulMessageProvider, String errorMessage) {
        Optional<UserDto> userDto = supplier.get();
        String message;
        if (userDto.isPresent()) {
            message = successfulMessageProvider.apply(userDto.get());
        } else {
            message = errorMessage;
        }
        return message;
    }
}
