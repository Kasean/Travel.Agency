package com.kasean.test.service;

import com.kasean.test.model.Tour;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface TourService {

    Iterable<Tour> findAll();

    List<Tour> findByDirection(String direction);

    Optional<Tour> findById(Long id);

    Tour createTour(String direction, LocalDate date, Integer coast);

    Tour updateTour(Long tour_id, String direction, LocalDate date, Integer coast);



    void deleteTour(Long tour_id);

}
