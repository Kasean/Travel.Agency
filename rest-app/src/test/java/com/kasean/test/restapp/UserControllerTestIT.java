package com.kasean.test.restapp;

import com.kasean.test.restapp.controllers.TourController;
import com.kasean.test.restapp.controllers.UserController;
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
public class UserControllerTestIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserController userController;

    @Test
    public void testInit() throws Exception{
        assertThat(userController).isNotNull();
    }

    @Test
    public void shouldReturnPoolOfTestUsers() throws Exception {
        this.mockMvc.perform(get("/createTestUsers"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Joe@user.com")));;
    }

    @Test
    public void shouldReturnAllUsers() throws Exception {
        this.mockMvc.perform(get("/users"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("[]")));;
    }
}
