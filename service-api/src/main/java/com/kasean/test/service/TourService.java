package com.kasean.test.service;

import com.kasean.test.model.Tour;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface TourService {

    Iterable<Tour> findAll();

    List<Tour> findByDirection(String direction);

    Optional<Tour> findById(Long id);

    Long createTour(Tour tour);

    Long updateTour(Tour tour);

    Long deleteTour(Long tour_id);

}
