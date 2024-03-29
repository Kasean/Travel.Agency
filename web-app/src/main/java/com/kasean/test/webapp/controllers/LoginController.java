package com.kasean.test.webapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    @GetMapping("/")
    public String showLoginPage(Model model) {
        return "login";
    }

    @PostMapping("/")
    public String doLogin(@RequestParam String login, String password, Model model){

        if((login.equals("admin@admin.com")) && (password.equals("admin"))){
            return "admin-main";
        }
            else if ((login.equals("user@user.com")) && (password.equals("user"))){
                return "main";
            }
        else {
            return "login";
        }
    }


}
