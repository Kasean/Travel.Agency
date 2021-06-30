package com.kasean.test.impl;


import com.kasean.test.dao.TourDao;
import com.kasean.test.dao.UserDao;
import com.kasean.test.model.Tour;
import com.kasean.test.service.TourService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TourRestServiceImpl implements TourService {

    @Autowired
    private TourDao tourDao;

    @Override
    public Iterable<Tour> findAll() {

        return tourDao.findAll();
    }

    @Override
    public List<Tour> findByDirection(String direction) {

        Iterable<Tour> tours = tourDao.findAll();
        List<Tour> temp = new ArrayList<>();
        List<Tour> result = new ArrayList<>();
        tours.forEach(temp::add);

        for (Tour i : temp) {
            if (i.getDirection().equals(direction)) {
                result.add(i);
            }
        }

        return result;
    }

    @Override
    public Optional<Tour> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public Tour createTour(String direction, LocalDate date, Integer coast) {
        Tour tour = new Tour(direction, date, coast);
        return tourDao.save(tour);
    }

    @Override
    public Tour updateTour(Long tour_id, String direction, LocalDate date, Integer coast) {

        Tour tour = tourDao.findById(tour_id).orElseThrow();
        tour.setDirection(direction);
        tour.setDate(date);
        tour.setCoast(coast);

        return tourDao.save(tour);
    }

    @Override
    public void deleteTour(Long tour_id) {
        Tour tour = tourDao.findById(tour_id).orElseThrow();
        tourDao.delete(tour);
    }
}
