package com.kasean.test.service;

import com.kasean.test.dao.TourDao;
import com.kasean.test.dao.UserDao;
import com.kasean.test.model.Tour;
import com.kasean.test.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {


    @Autowired
    private UserDao userDao;

    @Autowired
    private TourDao tourDao;

    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public Iterable<User> findAll() {

        return userDao.findAll();

    }

    @Override
    public Long byTour(Long tour_id, Long user_id) {
        LOGGER.debug("User: {} buy tour: {}", user_id, tour_id);
        Tour tour = tourDao.findById(tour_id).orElseThrow();

        tour.setUser_id(user_id);
        tourDao.save(tour);
        return tour.getId();

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
    public Long createUser(User user) {
        LOGGER.debug("Create user {}", user);
        userDao.save(user);
        return user.getId();
    }


}
