package com.kasean.test.service;

import com.kasean.test.dao.TourDao;
import com.kasean.test.model.Tour;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.ContextConfiguration;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
public class TourServiceImplTestIT {

    @Mock
    private static TourDao tourDao;

    @InjectMocks
    private static TourServiceImpl tourService;

    private Tour tour;

    @Test
    public void testFindById(){

        Long tourId = new Long(1L);

        tour = new Tour();

        tour.setId(tourId);
        Mockito.when(tourDao.findById(tourId)).thenReturn(java.util.Optional.ofNullable(tour));

        Optional<Tour> retrivedTour = tourService.findById(tourId);
        List<Tour> tours = new ArrayList<>();
        retrivedTour.ifPresent(tours::add);

        Assert.assertEquals(tour, tours.get(0));

    }

}
