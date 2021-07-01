package com.kasean.test.impl;

import com.kasean.test.dao.TourDao;
import com.kasean.test.dao.UserDao;
import com.kasean.test.model.Tour;
import com.kasean.test.model.User;
import com.kasean.test.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserRestServiceImpl implements UserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserRestServiceImpl.class);

    @Override
    public Iterable<User> findAll() {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8088";
        LOGGER.debug("find all users");
        ResponseEntity responseEntity = restTemplate.getForEntity(url, List.class);
        return (List<User>) responseEntity.getBody();
    }

    @Override
    public Long byTour(Long tour_id, Long user_id) {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8088/buy-tour";
        LOGGER.debug("user: {} buy tour: {}", user_id, tour_id);
        BuyTourDto buyTourDto = new BuyTourDto(user_id, tour_id);
        ResponseEntity responseEntity = restTemplate.postForEntity(url, buyTourDto, Long.class);
        return (Long) responseEntity.getBody();

    }

    @Override
    public List<Tour> showMyTour(Long user_id) {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8088/Basket";
        LOGGER.debug("findAllTours()");
        ResponseEntity responseEntity = restTemplate.postForEntity(url, user_id, List.class);
        return (List) responseEntity.getBody();
    }

    @Override
    public Long createUser(User user) {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8088";

        LOGGER.debug("create({})", user);
        ResponseEntity responseEntity = restTemplate.postForEntity(url, user, Long.class);
        return (Long) responseEntity.getBody();
    }
}
