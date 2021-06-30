package com.kasean.test.impl;

import com.kasean.test.dao.TourDao;
import com.kasean.test.dao.UserDao;
import com.kasean.test.model.Tour;
import com.kasean.test.model.User;
import com.kasean.test.service.UserService;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserRestServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private TourDao tourDao;


    @Override
    public Iterable<User> findAll() {
        return userDao.findAll();
    }

    @Override
    public Tour byTour(Long tour_id, Long user_id) {

        Tour tour = tourDao.findById(tour_id).orElseThrow();

        tour.setUser_id(user_id);
        return tourDao.save(tour);
    }

    @Override
    public List<Tour> showMyTour(Long user_id) {
        Iterable<Tour> tourIterable = tourDao.findAll();
        List<Tour> temp = new ArrayList<>();
        tourIterable.forEach(temp::add);

        List<Tour> result = new ArrayList<>();

        for (Tour i : temp) {
            if (i.getUser_id() == user_id) {
                result.add(i);
            }
        }

        return result;
    }

    @Override
    public User createUser(String user_name, String user_pass, Integer is_admin) {

        User user = new User(user_name, user_pass, is_admin);
        return userDao.save(user);
    }
}
