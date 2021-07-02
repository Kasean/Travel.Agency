package com.kasean.test.impl.config;

import com.kasean.test.impl.TourRestServiceImpl;
import com.kasean.test.impl.UserRestServiceImpl;
import com.kasean.test.service.TourService;
import com.kasean.test.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@Configuration
public class TestConfig {

    @Bean
    RestTemplate restTemplate() {
        return new RestTemplate(new SimpleClientHttpRequestFactory());
    }

    @Bean
    TourService tourService() {
        return new TourRestServiceImpl(restTemplate());
    }

    @Bean
    UserService userService(){
        return new UserRestServiceImpl(restTemplate());
    }

}
