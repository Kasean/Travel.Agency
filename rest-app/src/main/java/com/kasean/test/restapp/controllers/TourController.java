package com.kasean.test.restapp.controllers;

import com.kasean.test.model.Tour;
import com.kasean.test.service.TourService;
import com.kasean.test.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
public class TourController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

//    @Bean(name = "entityManagerFactory")
//    public LocalSessionFactoryBean sessionFactory() {
//        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
//
//        return sessionFactory;
//    }

    @Autowired
    @Qualifier("tourRestServiceImpl")
    private TourService tourService;

    @GetMapping("createTestTours")
    public List<Tour> createTestTours() {
        List<Tour> tours = new ArrayList<>(5);
        tours.add(tourService.createTour("Brest-Canada", LocalDate.of(2022, 07, 01), 4000));
        tours.add(tourService.createTour("Brest-Grodno", LocalDate.of(2021, 07, 10), 200));
        tours.add(tourService.createTour("Brest-Gdansk", LocalDate.of(2021, 10, 01), 1200));
        tours.add(tourService.createTour("Brest-Kair", LocalDate.of(2021, 07, 01), 800));
        tours.add(tourService.createTour("Brest-Moscow", LocalDate.of(2021, 07, 01), 400));
        return tours;
    }

    @PostMapping(path = "/createTour", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Tour> createTour(@RequestBody Tour tour) {
        //LocalDate tourDate = LocalDate.parse(date);
        Tour tour1 = tourService.createTour(tour.getDirection(), tour.getDate(), tour.getCoast());
        return new ResponseEntity<>(tour1, HttpStatus.OK);
    }

    @PutMapping(value = "/updateTour", consumes = {"application/json"}, produces = {"application/json"})
    public ResponseEntity<Tour> updateTour(@RequestBody Tour tour) {
        Tour tour1 = tourService.updateTour(tour.getId(), tour.getDirection(), tour.getDate(), tour.getCoast());
        return new ResponseEntity<>(tour1, HttpStatus.OK);
    }

    @DeleteMapping(value = "/deleteTour/{id}", produces = {"application/json"})
    public ResponseEntity<Long> deleteTour(@PathVariable(value = "id") Long id) {
        tourService.deleteTour(id);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }

    @GetMapping("/Search/{direction}")
    public List<Tour> search(@PathVariable(value = "direction") String direction) {
        List<Tour> tours = tourService.findByDirection(direction);
        return tours;
    }

    @GetMapping("/ShowAllTours")
    public List<Tour> findAll() {
        Iterable<Tour> tourIterable = tourService.findAll();
        List<Tour> result = new ArrayList<>();
        tourIterable.forEach(result::add);
        return result;
    }
}
