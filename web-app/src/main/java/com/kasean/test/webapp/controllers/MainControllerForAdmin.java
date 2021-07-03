package com.kasean.test.webapp.controllers;

import com.kasean.test.model.Tour;
import com.kasean.test.service.TourService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class MainControllerForAdmin {

    private TourService tourService;

    @Autowired
    public MainControllerForAdmin(TourService tourService) {
        this.tourService = tourService;
    }

    @GetMapping("/AdminMain")
    public String showAdminMainPage(Model model){

        Iterable<Tour> tours = tourService.findAll();

        model.addAttribute("tours", tours);

        return "admin-main";
    }

    @GetMapping("/Add")
    public String showAddPage(Model model){
        return "add";
    }

    @PostMapping("/Add")
    public String addTour(@RequestParam String direction, String date, Integer coast, Model model){

        LocalDate tourDate = LocalDate.parse(date);

        tourService.createTour(new Tour(direction, tourDate, coast));

        return "add";
    }

    @GetMapping("/Change/{id}")
    public String changeTour(@PathVariable(value = "id") long id, Model model){

        Optional<Tour> tour = tourService.findById(id);
        List<Tour> tours = new ArrayList<>();
        tour.ifPresent(tours::add);
        model.addAttribute("tours", tours);

        return "change";
    }

    @PostMapping("/Change/{id}")
    public String updateTour(@PathVariable(value = "id") long id, @RequestParam String direction, String date, Integer coast, Model model){
        LocalDate tourDate = LocalDate.parse(date);
        Tour tour = new Tour(direction, tourDate, coast);
        tour.setId(id);
        tourService.updateTour(tour);
        return "admin-main";

    }

    @PostMapping("/Delete/{id}")
    public String deleteTour(@PathVariable(value = "id") long id, Model model){
        tourService.deleteTour(id);

        return "admin-main";

    }

    @PostMapping("/AdminMain")
    public String search(@RequestParam String desiredDirection, Model model){

        List<Tour> tours = tourService.findByDirection(desiredDirection);

        model.addAttribute("tours", tours);

        return "admin-main";
    }
}
