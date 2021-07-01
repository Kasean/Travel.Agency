package com.kasean.test.webapp;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
@ComponentScan(basePackages = "com.kasean.test")
public class WebAppApplication{

    public static void main(String[] args) {
        SpringApplication.run(WebAppApplication.class, args);
    }

}
