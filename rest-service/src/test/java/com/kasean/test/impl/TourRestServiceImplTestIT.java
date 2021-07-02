package com.kasean.test.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kasean.test.impl.config.TestConfig;
import com.kasean.test.model.Tour;
import com.kasean.test.service.TourService;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.client.ExpectedCount;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withStatus;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = TestConfig.class)
public class TourRestServiceImplTestIT {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    TourService tourService;

    private MockRestServiceServer mockServer;

    private ObjectMapper mapper = new ObjectMapper();

    @BeforeEach
    public void before() {
        mockServer = MockRestServiceServer.createServer(restTemplate);
    }

    @Test
    public void shouldReturnAllTours() throws Exception {
        // given
        mockServer.expect(ExpectedCount.once(), requestTo(new URI("http://localhost:8088/ShowAllTours")))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withStatus(HttpStatus.OK)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(mapper.writeValueAsString(Arrays.asList(
                                create(0L),
                                create(1L))))
                );
        // when
        List<Tour> tours = (List<Tour>) tourService.findAll();

        // then

        mockServer.verify();
        assertNotNull(tours);
        assertTrue(tours.size() > 0);

    }

    @Test
    public void shouldFindTourById() throws URISyntaxException, JsonProcessingException {

        // given
        Long id = 1L;
        Tour tour = new Tour();

        tour.setDirection("direction");

        tour.setId(id);
        mockServer.expect(ExpectedCount.once(), requestTo(new URI("http://localhost:8088/findById" + "/" + id)))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withStatus(HttpStatus.OK)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(mapper.writeValueAsString(tour))
                );

        // when
        Optional<Tour> optionalTour = tourService.findById(id);

        // then
        mockServer.verify();
        assertTrue(optionalTour.isPresent());
        assertEquals(optionalTour.get().getId(), id);
        assertEquals(optionalTour.get().getDirection(), tour.getDirection());
    }

    @Test
    public void shouldFindTourByDirection() throws URISyntaxException, JsonProcessingException {
        // given
        String direction = "direction";
        Tour tour = new Tour();

        tour.setDirection(direction);

        mockServer.expect(ExpectedCount.once(), requestTo(new URI("http://localhost:8088/Search" + "/" + direction)))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withStatus(HttpStatus.OK)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(mapper.writeValueAsString(Arrays.asList(
                                create(0L),
                                create(1L))))
                );

        // when
        List<Tour> tours = tourService.findByDirection(direction);

        // then
        mockServer.verify();
        assertNotNull(tours);
        assertTrue(tours.size() > 0);
    }

    @Test
    public void shouldCreateTour() throws Exception {
        // given
        Tour tour = create(0L);

        mockServer.expect(ExpectedCount.once(), requestTo(new URI("http://localhost:8088/createTour")))
                .andExpect(method(HttpMethod.POST))
                .andRespond(withStatus(HttpStatus.OK)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(mapper.writeValueAsString("1"))
                );
        // when
        Long id = tourService.createTour(tour);

        // then
        mockServer.verify();
        assertNotNull(id);
    }

    @Test
    public void shouldUpdateTour() throws Exception {

        // given
        Long id = 1L;
        Tour tour = new Tour();
        tour.setId(id);

        mockServer.expect(ExpectedCount.once(), requestTo(new URI("http://localhost:8088/updateTour")))
                .andExpect(method(HttpMethod.PUT))
                .andRespond(withStatus(HttpStatus.OK)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(mapper.writeValueAsString("1"))
                );

        mockServer.expect(ExpectedCount.once(), requestTo(new URI("http://localhost:8088/findById" + "/" + id)))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withStatus(HttpStatus.OK)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(mapper.writeValueAsString(tour))
                );

        // when
        Long result = tourService.updateTour(tour);
        Optional<Tour> updatedTourOptional = tourService.findById(id);

        // then
        mockServer.verify();
        assertTrue(1 == result);

        assertTrue(updatedTourOptional.isPresent());
        assertEquals(updatedTourOptional.get().getId(), id);
        assertEquals(updatedTourOptional.get().getDirection(), tour.getDirection());
    }

    @Test
    public void shouldDeleteTour() throws Exception {

        // given
        Long id = 1L;
        mockServer.expect(ExpectedCount.once(), requestTo(new URI("http://localhost:8088/deleteTour" + "/" + id)))
                .andExpect(method(HttpMethod.DELETE))
                .andRespond(withStatus(HttpStatus.OK)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(mapper.writeValueAsString("1"))
                );
        // when
        Long result = tourService.deleteTour(id);

        // then
        mockServer.verify();
        assertTrue(1L == result);
    }

    private Tour create(Long index) {
        Tour tour = new Tour();

        tour.setId(index);
        tour.setDirection("direction");
        tour.setDate(LocalDate.of(2021, 10, 10));
        tour.setCoast(200);

        return tour;
    }

}
