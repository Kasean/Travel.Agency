package com.kasean.test.impl;


import com.kasean.test.model.Tour;
import com.kasean.test.service.TourService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class TourRestServiceImpl implements TourService {

    private static final Logger LOGGER = LoggerFactory.getLogger(TourRestServiceImpl.class);

//    private String url;
//
//    private RestTemplate restTemplate;
//
//    public TourRestServiceImpl() {
//    }
//
//    public TourRestServiceImpl(String url, RestTemplate restTemplate) {
//        this.url = url;
//        this.restTemplate = restTemplate;
//    }

    @Override
    public Iterable<Tour> findAll() {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8088/ShowAllTours";
        LOGGER.debug("findAllTours()");
        ResponseEntity responseEntity = restTemplate.getForEntity(url, Iterable.class);
        return (Iterable<Tour>) responseEntity.getBody();

    }

    @Override
    public List<Tour> findByDirection(String direction) {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8088/Search/" + direction;
        LOGGER.debug("find tours by direction: {}", direction);
        ResponseEntity responseEntity = restTemplate.getForEntity(url, List.class);
        return (List<Tour>) responseEntity.getBody();

    }

    @Override
    public Optional<Tour> findById(Long id) {

        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8088/findById/";
        LOGGER.debug("find by id ({})", id);
        ResponseEntity<Tour> responseEntity =
                restTemplate.getForEntity(url + id, Tour.class);

        return Optional.ofNullable(responseEntity.getBody());
    }

    @Override
    public Long createTour(Tour tour) {
        LOGGER.debug("create tour: {} ", tour);
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8088/createTour";
        ResponseEntity responseEntity = restTemplate.postForEntity(url, tour, Long.class);
        return (Long) responseEntity.getBody();
    }

    @Override
    public Long updateTour(Tour tour) {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8088/updateTour";

        LOGGER.debug("update ({})", tour);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<Tour> entity = new HttpEntity<>(tour, httpHeaders);
        ResponseEntity<Long> result = restTemplate.exchange(url, HttpMethod.PUT, entity, Long.class);
        return result.getBody();

    }

    @Override
    public Long deleteTour(Long tour_id) {

        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8088//deleteTour";

        LOGGER.debug("delete ({})", tour_id);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<Tour> entity = new HttpEntity<>(httpHeaders);
        ResponseEntity<Long> result =
                restTemplate.exchange(url + "/" + tour_id, HttpMethod.DELETE, entity, Long.class);
        return result.getBody();

    }
}
