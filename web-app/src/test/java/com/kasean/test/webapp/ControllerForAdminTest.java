package com.kasean.test.webapp;

import com.kasean.test.webapp.controllers.MainControllerForAdmin;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
public class ControllerForAdminTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private MainControllerForAdmin mainControllerForAdmin;

    @Test
    public void testInit() throws Exception{
        assertThat(mainControllerForAdmin).isNotNull();
    }

    @Test
    public void shouldReturnMainPage() throws Exception {
        this.mockMvc.perform(get("/AdminMain"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("ТУРЫ")));;
    }

    @Test
    public void shouldReturnAddPage() throws Exception {
        this.mockMvc.perform(get("/Add"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Форма добавления тура")));;
    }



}