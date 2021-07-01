package com.kasean.test.restapp.controllers;

import com.kasean.test.impl.UserRestServiceImpl;
import com.kasean.test.model.Tour;
import com.kasean.test.model.User;
import com.kasean.test.restapp.dto.BuyTourDto;
import com.kasean.test.service.TourService;
import com.kasean.test.service.UserService;
import com.kasean.test.service.UserServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
public class UserController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

//    @Bean(name="entityManagerFactory")
//    public LocalSessionFactoryBean sessionFactory() {
//        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
//
//        return sessionFactory;
//    }

    @Autowired
    @Qualifier("userServiceImpl")
    private UserService userService;

    @Autowired
    @Qualifier("tourRestServiceImpl")
    private TourService tourService;


    @GetMapping(value = "users")
    public final Iterable<User> users(){

        LOGGER.debug("users()");
        return userService.findAll();
    }

    @PostMapping(value = "/create-user", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<User> createUser(@RequestBody User user){
        Long newUserId = userService.createUser(user);
        return new ResponseEntity<>(user, HttpStatus.OK);

    }

    @PostMapping(value = "/Basket", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<Tour>> showUserBasket(@RequestBody Long id){
        List<Tour> tours = userService.showMyTour(id);
        return new ResponseEntity<>(tours, HttpStatus.OK);
    }

    @PostMapping(value = "/buy-tour", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Long> buyTour(@RequestBody BuyTourDto dto){
        Long tour_id = userService.byTour(dto.getTour_id(), dto.getUser_id());
        return new ResponseEntity<>(tour_id, HttpStatus.OK);
    }



}
