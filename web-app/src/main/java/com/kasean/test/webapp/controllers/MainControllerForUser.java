package com.kasean.test.webapp.controllers;

import com.kasean.test.model.Tour;
import com.kasean.test.service.TourServiceImpl;
import com.kasean.test.service.UserServiceImpl;
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

    @Autowired
    private TourServiceImpl tourService;
    @Autowired
    private UserServiceImpl userService;

    @GetMapping("/main")
    public String showMainPage(Model model){

        Iterable<Tour> tours = tourService.findAll();

        model.addAttribute("tours", tours);


        return "main";
    }


    @GetMapping("/AboutUs")
    public String showAboutUsPage(Model model){
        return "AboutUs";
    }


    @GetMapping("/MyTour")
    public String showMyTourPage(Model model){

        List<Tour> tours = userService.showMyTour(0L);

        model.addAttribute("tours", tours);

        return "MyTour";
    }

    @PostMapping("/Buy/{id}")
    public String buyTout(@PathVariable(value = "id") Long id, Model model){

        userService.byTour(id, 0L);

        return "main";
    }

    @PostMapping("main")
    public String searchInAll(@RequestParam String desiredDirection, Model model){

        List<Tour> tours = tourService.findByDirection(desiredDirection);

        model.addAttribute("tours", tours);

        return "main";
    }

}
