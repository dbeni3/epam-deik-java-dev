package com.epam.training.ticketservice.core.configuration;

import com.epam.training.ticketservice.core.movie.persistance.entity.Movie;
import com.epam.training.ticketservice.core.movie.persistance.repository.MovieRepository;
import com.epam.training.ticketservice.core.room.persistance.entity.Room;
import com.epam.training.ticketservice.core.room.persistance.repositpry.RoomRepository;
import com.epam.training.ticketservice.core.user.persistence.entity.User;
import com.epam.training.ticketservice.core.user.persistence.repository.UserRepository;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

@Component
public class InMemoryDbInitializer {

    private final UserRepository userRepository;
    private final MovieRepository movieRepository;
    private final RoomRepository roomRepository;

    public InMemoryDbInitializer(UserRepository userRepository,
                                 MovieRepository movieRepository,
                                 RoomRepository roomRepository) {
        this.userRepository = userRepository;
        this.movieRepository = movieRepository;
        this.roomRepository = roomRepository;
    }


    @PostConstruct
    public void init() {
        Movie movie1 = new Movie(null, "A remény rabjai", "Drama", 142);
        Movie movie2 = new Movie(null, "A keresztapa", "Drama", 100);
        Movie one = new Movie(null, "one", "Drama", 100);
        Room west = new Room(null,"West",10,10);
        Room east = new Room(null,"East",8,13);
        movieRepository.saveAll(List.of(one,movie1,movie2));
        roomRepository.saveAll(List.of(west,east));

        User admin = new User(null, "admin", "admin", User.Role.ADMIN);
        userRepository.save(admin);
    }
}
