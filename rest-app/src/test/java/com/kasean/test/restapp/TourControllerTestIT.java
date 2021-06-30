package com.kasean.test.restapp;

import com.kasean.test.restapp.controllers.HomeController;
import com.kasean.test.restapp.controllers.TourController;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class TourControllerTestIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TourController tourController;

    @Test
    public void testInit() throws Exception{
        assertThat(tourController).isNotNull();
    }

    @Test
    public void shouldReturnPoolOfTestTours() throws Exception {
        this.mockMvc.perform(get("/createTestTours"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Brest-Canada")));;
    }

    @Test
    public void shouldReturnAllTours() throws Exception {
        this.mockMvc.perform(get("/ShowAllTour"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("[]")));;
    }
}
