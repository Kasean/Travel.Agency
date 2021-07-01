package com.kasean.test.restapp.controllers;

import com.kasean.test.model.Tour;
import com.kasean.test.restapp.dto.UpdateTourDto;
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
import java.util.Optional;

@RestController
public class TourController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    @Qualifier("tourServiceImpl")
    private TourService tourService;

    @PostMapping(path = "/createTour", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Long> createTour(@RequestBody Tour tour) {
        //LocalDate tourDate = LocalDate.parse(date);
        Long newTourId = tourService.createTour(tour);
        return new ResponseEntity<>(newTourId, HttpStatus.OK);
    }

    @PutMapping(value = "/updateTour", consumes = {"application/json"}, produces = {"application/json"})
    public ResponseEntity<Long> updateTour(@RequestBody Tour tour) {
        Long updateTourId = tourService.updateTour(tour);
        return new ResponseEntity<>(updateTourId, HttpStatus.OK);
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

    @GetMapping("/findById/{id}")
    public Optional<Tour> findById(@PathVariable(value = "id") Long id){
        Optional<Tour> tour = tourService.findById(id);
        return tour;
    }

    @GetMapping("/ShowAllTours")
    public ResponseEntity<List<Tour>> findAll() {
        Iterable<Tour> tourIterable = tourService.findAll();
        List<Tour> result = new ArrayList<>();
        tourIterable.forEach(result::add);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
