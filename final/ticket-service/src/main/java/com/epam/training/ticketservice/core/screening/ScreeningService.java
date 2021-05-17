package com.epam.training.ticketservice.core.screening;

import com.epam.training.ticketservice.core.movie.model.MovieDto;

import com.epam.training.ticketservice.core.room.model.RoomDto;
import com.epam.training.ticketservice.core.screening.model.ScreeningDto;

public interface ScreeningService {
    void createScreening(ScreeningDto screeningDto);
}
