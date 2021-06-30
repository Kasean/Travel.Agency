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
    @Qualifier("userRestServiceImpl")
    private UserService userService;

    @Autowired
    @Qualifier("tourRestServiceImpl")
    private TourService tourService;


    @GetMapping(value = "users")
    public final Iterable<User> users(){

        LOGGER.debug("users()");
        return userService.findAll();
    }

    @GetMapping("createTestUsers")
    public List<User> createTestUsers(){
        List<User> users = new ArrayList<>(2);
        users.add(userService.createUser("Bob@user.com", "user", 0));
        users.add(userService.createUser("Joe@user.com", "admin", 1));
        return users;
    }

    @PostMapping(value = "/create-user", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<User> createUser(@RequestBody User user){
        User user1 = userService.createUser(user.getUser_name(), user.getUser_pass(), 0);
        return new ResponseEntity<>(user1, HttpStatus.OK);

    }

    @GetMapping(value = "/Basket/{id}")
    public List<Tour> showUserBasket(@PathVariable Long id){
        List<Tour> tours = userService.showMyTour(id);
        return tours;
    }

    @PostMapping(value = "/buy-tour", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Tour> buyTour(@RequestBody BuyTourDto dto){
        Tour tour = userService.byTour(dto.getTour_id(), dto.getUser_id());
        return new ResponseEntity<>(tour, HttpStatus.OK);
    }



}
