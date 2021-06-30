package com.kasean.test.service;

import com.kasean.test.model.Tour;
import com.kasean.test.model.User;

import java.util.List;

public interface UserService {

    Iterable<User> findAll();

    Tour byTour(Long tour_id, Long user_id);

    List<Tour> showMyTour(Long user_id);

    User createUser(String user_name, String user_pass, Integer is_Admin);

}
