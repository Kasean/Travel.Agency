package com.kasean.test.restapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@ComponentScan({"com.kasean.test"})
@EntityScan("com.kasean.test.model")
@EnableJpaRepositories("com.kasean.test.dao")
@RequestMapping(value = "", produces = "application/json")
public class RestAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(RestAppApplication.class, args);
    }

}
