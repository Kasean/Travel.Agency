package com.kasean.test.webapp.controllers;

import com.kasean.test.model.User;
import com.kasean.test.service.UserServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class LoginController {

    @Autowired
    private UserServiceImpl userService;


    @GetMapping("/login")
    public String showLoginPage(Model model) {
        return "login";
    }

    @PostMapping("/login")
    public String doLogin(@RequestParam String login, String password, Model model){



        if((login.equals("admin@admin.com")) && (password.equals("admin"))){
            return "AdminMain";
        }
            else if ((login.equals("user@user.com")) && (password.equals("user"))){
                return "main";
            }
        else {
            return "login";
        }
    }


}
