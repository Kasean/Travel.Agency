package com.kasean.test.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

public class TourTest {

    @Test
    public void createTout(){

        Tour tour = new Tour();
        tour.setId(1L);
        tour.setCoast(2000);
        tour.setDate(LocalDate.of(2021, 6, 3));
        tour.setDirection("Brest-Grodno");

        Assertions.assertEquals(1L, tour.getId());
        Assertions.assertEquals(2000, tour.getCoast());
        Assertions.assertEquals("Brest-Grodno", tour.getDirection());
        Assertions.assertEquals(LocalDate.of(2021, 6, 3), tour.getDate());

    }

    @Test
    public void createTourWithData(){

        Tour tour = new Tour("Brest-Moscow", LocalDate.of(2021, 07, 20), 2000);
        Assertions.assertEquals(2000, tour.getCoast());
        Assertions.assertEquals("Brest-Moscow", tour.getDirection());
        Assertions.assertEquals(LocalDate.of(2021, 07, 20), tour.getDate());

    }

}
