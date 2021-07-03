package com.kasean.test.restapp;

import com.kasean.test.model.Tour;
import com.kasean.test.model.User;
import com.kasean.test.service.TourService;
import com.kasean.test.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTestIT {

    @Autowired
    @Qualifier("userServiceImpl")
    private UserService userService;

    @Qualifier("tourServiceImpl")
    @Autowired
    private TourService tourService;

    @Test
    public void testInit() {
        assertThat(userService).isNotNull();
    }

    @Test
    public void findAll() {
        Iterable<User> users = userService.findAll();
        List<User> result = new ArrayList<>();
        users.forEach(result::add);
        assertNotNull(result);
        assertTrue(result.size() > 0);
    }

    @Test
    public void createUser() {
        Iterable<User> users = userService.findAll();
        List<User> result = new ArrayList<>();
        users.forEach(result::add);
        assertNotNull(result);
        assertTrue(result.size() > 0);

        userService.createUser(new User("Bob@user.com", "pass", 0));

        Iterable<User> userIterable = userService.findAll();
        List<User> real = new ArrayList<>();
        userIterable.forEach(real::add);
        assertNotNull(real);
        assertTrue(real.size() > 0);
        assertEquals(result.size() + 1, real.size());
    }

    @Test
    public void buyTour() {
        Iterable<Tour> tourIterable = tourService.findAll();
        List<Tour> tourList = new ArrayList<>();
        tourIterable.forEach(tourList::add);
        assertTrue(tourList.size() > 0);

        Long tour_id = tourList.get(0).getId();

        Iterable<User> userIterable = userService.findAll();
        List<User> userList = new ArrayList<>();
        userIterable.forEach(userList::add);
        assertNotNull(userList);
        assertTrue(userList.size() > 0);

        Long user_id = userList.get(1).getId();

        userService.byTour(tour_id, user_id);

        Iterable<Tour> tours = tourService.findAll();
        List<Tour> result = new ArrayList<>();
        tours.forEach(result::add);
        assertTrue(result.size() > 0);

        assertTrue(result.get(0).getUser_id() == user_id);

    }

    @Test
    public void showUserBasket() {

        Iterable<Tour> tourIterable = tourService.findAll();
        List<Tour> tourList = new ArrayList<>();
        tourIterable.forEach(tourList::add);
        assertTrue(tourList.size() > 0);

        Long tour_id = tourList.get(0).getId();

        Iterable<User> userIterable = userService.findAll();
        List<User> userList = new ArrayList<>();
        userIterable.forEach(userList::add);
        assertNotNull(userList);
        assertTrue(userList.size() > 0);

        Long user_id = userList.get(1).getId();

        userService.byTour(tour_id, user_id);

        Iterable<Tour> tours = tourService.findAll();
        List<Tour> result = new ArrayList<>();
        tours.forEach(result::add);
        assertTrue(result.size() > 0);

        assertTrue(result.get(0).getUser_id() == user_id);

        List<Tour> basket = userService.showMyTour(user_id);
        assertTrue(basket.size() > 0);
        assertTrue(basket.get(0).getUser_id() == user_id);

    }

}
