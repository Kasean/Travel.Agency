package com.kasean.test.restapp;

import com.kasean.test.model.Tour;
import com.kasean.test.service.TourService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class TourControllerTestIT {

    @Qualifier("tourServiceImpl")
    @Autowired
    private TourService tourService;

    @Test
    public void testInit(){
        assertThat(tourService).isNotNull();
    }

    @Test
    public void shouldReturnAllTours() {
        Iterable<Tour> tours = tourService.findAll();
        List<Tour> result = new ArrayList<>();
        tours.forEach(result::add);
        assertNotNull(result);
        assertTrue(result.size() > 0);
    }

    @Test
    public void shouldCreateTour() {
        Iterable<Tour> tours = tourService.findAll();
        List<Tour> result = new ArrayList<>();
        tours.forEach(result::add);
        assertNotNull(result);
        assertTrue(result.size() > 0);

        tourService.createTour(new Tour("Brest-Grodno", LocalDate.of(2021, 01, 01),
                200));
        Iterable<Tour> tourIterable = tourService.findAll();
        List<Tour> real = new ArrayList<>();
        tourIterable.forEach(real::add);
        assertEquals(real.size(), result.size() + 1);
    }

    @Test
    public void shouldUpdateTour() {
        Iterable<Tour> tours = tourService.findAll();
        List<Tour> result = new ArrayList<>();
        tours.forEach(result::add);

        Tour tour = result.get(0);
        tour.setDirection("MOSCOW-BERLIN");
        tourService.updateTour(tour);

        Optional<Tour> realTour = tourService.findById(tour.getId());
        assertEquals(realTour.get().getDirection(), "MOSCOW-BERLIN");
    }

    @Test
    public void shouldSearchTour() {
        Iterable<Tour> tours = tourService.findAll();
        List<Tour> result = new ArrayList<>();
        tours.forEach(result::add);
        assertNotNull(result);
        assertTrue(result.size() > 0);

        List<Tour> tourList = tourService.findByDirection(result.get(0).getDirection());
        assertTrue(tourList.size() > 0);
        assertEquals(tourList.get(0).getId(), result.get(0).getId());
    }

    @Test
    public void findById() {
        Iterable<Tour> tours = tourService.findAll();
        List<Tour> result = new ArrayList<>();
        tours.forEach(result::add);
        assertNotNull(result);
        assertTrue(result.size() > 0);

        Long id = result.get(0).getId();
        Tour expTour = tourService.findById(id).get();
        assertEquals(id, expTour.getId());
        assertEquals(result.get(0).getDirection(), expTour.getDirection());
        assertEquals(result.get(0).getDate(), expTour.getDate());
        assertEquals(result.get(0).getCoast(), expTour.getCoast());

    }

    @Test
    public void shouldDeleteTour() {
        Iterable<Tour> tours = tourService.findAll();
        List<Tour> result = new ArrayList<>();
        tours.forEach(result::add);
        assertNotNull(result);
        assertTrue(result.size() > 0);

        tourService.deleteTour(1L);
        Iterable<Tour> tourIterable = tourService.findAll();
        List<Tour> real = new ArrayList<>();
        tourIterable.forEach(real::add);
        assertEquals(real.size(), result.size() - 1);
    }

}
