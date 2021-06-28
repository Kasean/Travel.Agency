package com.kasean.test.webapp;

import com.kasean.test.model.Tour;
import com.kasean.test.model.User;
import com.kasean.test.service.TourServiceImpl;
import com.kasean.test.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@ComponentScan({"com.kasean.test"})
@EntityScan("com.kasean.test.model")
@EnableJpaRepositories("com.kasean.test.dao")
@RestController
@RequestMapping(value = "", produces = "application/json")
public class WebAppApplication{

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    TourServiceImpl tourService;

    public static void main(String[] args) {
        SpringApplication.run(WebAppApplication.class, args);
    }

    @RequestMapping("create-users")
    public List<User> createUsers(){
        List<User> users = new ArrayList<>(2);
        users.add(userService.createUser("user@user.com", "user", 10000, 0));
        users.add(userService.createUser("admin@admin.com", "admin", 1000000, 1));
        return users;
    }

    @RequestMapping("create-tours")
    public List<Tour> createTours(){
        List<Tour> tours = new ArrayList<>(5);
        tours.add(tourService.createTour("Brest-Canada", LocalDate.of(2022, 07, 01), 4000));
        tours.add(tourService.createTour("Brest-Grodno", LocalDate.of(2021, 07, 10), 200));
        tours.add(tourService.createTour("Brest-Gdansk", LocalDate.of(2021, 10, 01), 1200));
        tours.add(tourService.createTour("Brest-Kair", LocalDate.of(2021, 07, 01), 800));
        tours.add(tourService.createTour("Brest-Moscow", LocalDate.of(2021, 07, 01), 400));
        return tours;
    }
}
