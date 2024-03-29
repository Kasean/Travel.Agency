package com.kasean.test.webapp;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.kasean.test.webapp.controllers.LoginController;
import com.kasean.test.webapp.controllers.MainControllerForUser;
import org.junit.jupiter.api.Test;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ControllerForUserIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private MainControllerForUser mainControllerForUser;

    @Test
    public void testInit() throws Exception{
        assertThat(mainControllerForUser).isNotNull();
    }

    @Test
    public void shouldReturnMainPage() throws Exception {
        this.mockMvc.perform(get("/main"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("ТУРЫ")));;
    }

    @Test
    public void shouldReturnAboutUsPage() throws Exception {
        this.mockMvc.perform(get("/AboutUs"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Выбирая отдых, доверяйте опыту профессионалов!")));;
    }

    @Test
    public void shouldReturnMyTourPage() throws Exception {
        this.mockMvc.perform(get("/MyTour"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Мои туры")));;
    }
}
