package com.kasean.test.restapp.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    private static final String VERSION = "1.0.0";

    @GetMapping("")
    public String getVersion(){
        return "Version: " + VERSION;
    }


}
