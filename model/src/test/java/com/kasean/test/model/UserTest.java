package com.kasean.test.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

public class UserTest {
    @Test
    public void createUser(){

        User user = new User();
        user.setUser_name("username");
        user.setUser_pass("pass");
        user.setIs_admin(1);
        Assertions.assertEquals(1, user.getIs_admin());
        Assertions.assertEquals("username", user.getUser_name());
        Assertions.assertEquals("pass", user.getUser_pass());

    }

    @Test
    public void createUserWithData(){

        User user = new User("username", "pass", 1);
        Assertions.assertEquals(1, user.getIs_admin());
        Assertions.assertEquals("username", user.getUser_name());
        Assertions.assertEquals("pass", user.getUser_pass());

    }
}
