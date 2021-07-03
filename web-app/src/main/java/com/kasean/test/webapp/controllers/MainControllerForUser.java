package com.kasean.test.webapp.controllers;

import com.kasean.test.model.Tour;
import com.kasean.test.service.TourService;
import com.kasean.test.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class MainControllerForUser {


    private TourService tourService;

    private UserService userService;

    @Autowired
    public MainControllerForUser(TourService tourService, UserService userService) {
        this.tourService = tourService;
        this.userService = userService;
    }

    @GetMapping("/main")
    public String showMainPage(Model model) {

        Iterable<Tour> tours = tourService.findAll();

        model.addAttribute("tours", tours);


        return "main";
    }


    @GetMapping("/AboutUs")
    public String showAboutUsPage(Model model) {
        return "about-us";
    }


    @GetMapping("/MyTour")
    public String showMyTourPage(Model model) {

        List<Tour> tours = userService.showMyTour(2L);

        model.addAttribute("tours", tours);

        return "my-tour";
    }

    @PostMapping("/Buy/{id}")
    public String buyTout(@PathVariable(value = "id") Long id, Model model) {

        userService.byTour(id, 2L);

        return "main";
    }

    @PostMapping("main")
    public String searchInAll(@RequestParam String desiredDirection, Model model) {

        List<Tour> tours = tourService.findByDirection(desiredDirection);

        model.addAttribute("tours", tours);

        return "main";
    }



}
