package com.epam.training.ticketservice.core.screening.persistance.repository;


import com.epam.training.ticketservice.core.room.persistance.entity.Room;
import com.epam.training.ticketservice.core.screening.persistance.entity.Screening;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface ScreeningRepository extends JpaRepository<Screening, Integer> {


}
