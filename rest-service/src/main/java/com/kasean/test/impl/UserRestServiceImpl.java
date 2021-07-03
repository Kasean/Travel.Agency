package com.kasean.test.impl;

import com.kasean.test.impl.config.RestServiceConfig;
import com.kasean.test.model.Tour;
import com.kasean.test.model.User;
import com.kasean.test.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class UserRestServiceImpl implements UserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserRestServiceImpl.class);

    private RestTemplate restTemplate;

    public UserRestServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public Iterable<User> findAll() {
        String url = "http://localhost:8088/users";
        LOGGER.debug("find all users");
        ResponseEntity responseEntity = restTemplate.getForEntity(url, List.class);
        return (List<User>) responseEntity.getBody();
    }

    @Override
    public Long byTour(Long tour_id, Long user_id) {
        String url = "http://localhost:8088/buy-tour";
        LOGGER.debug("user: {} buy tour: {}", user_id, tour_id);
        BuyTourDto buyTourDto = new BuyTourDto(user_id, tour_id);
        ResponseEntity responseEntity = restTemplate.postForEntity(url, buyTourDto, Long.class);
        return (Long) responseEntity.getBody();

    }

    @Override
    public List<Tour> showMyTour(Long user_id) {
        String url = "http://localhost:8088/Basket";
        LOGGER.debug("findAllTours()");
        ResponseEntity responseEntity = restTemplate.postForEntity(url, user_id, List.class);
        return (List) responseEntity.getBody();
    }

    @Override
    public Long createUser(User user) {
        String url = "http://localhost:8088/create-user";

        LOGGER.debug("create({})", user);
        ResponseEntity responseEntity = restTemplate.postForEntity(url, user, Long.class);
        return (Long) responseEntity.getBody();
    }
}
