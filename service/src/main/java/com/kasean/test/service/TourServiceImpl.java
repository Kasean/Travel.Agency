package com.kasean.test.service;

import com.kasean.test.dao.TourDao;
import com.kasean.test.model.Tour;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
@Transactional
public class TourServiceImpl implements TourService {


    @Autowired
    private TourDao tourDao;
    private static final Logger LOGGER = LoggerFactory.getLogger(TourServiceImpl.class);

    @Override
    public Iterable<Tour> findAll() {

        LOGGER.trace("findAll()");
        return tourDao.findAll();
    }

    @Override
    public Optional<Tour> findById(Long id) {

        LOGGER.trace("findById(Id: {})", id);
        return tourDao.findById(id);
    }

    @Override
    public Long createTour(Tour tour) {

        LOGGER.debug("Create tour(Direction:{}, Date:{}, Coast:{})", tour.getDirection(), tour.getDate(), tour.getCoast());
        tourDao.save(tour);
        return tour.getId();
    }

    @Override
    public Long updateTour(Tour tour) {

        LOGGER.debug("Update tour {}", tour);

        Tour tour1 = tourDao.findById(tour.getId()).orElseThrow();
        tour1.setDirection(tour.getDirection());
        tour1.setDate(tour.getDate());
        tour1.setCoast(tour.getCoast());
        tourDao.save(tour1);
        return tour1.getId();
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
    public Long deleteTour(Long tour_id) {

        LOGGER.debug("Delete tour(tour_id:{})", tour_id);
        Tour tour = tourDao.findById(tour_id).orElseThrow();
        tourDao.delete(tour);
        return tour_id;
    }
}
