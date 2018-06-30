package com.joanadantas;

import com.joanadantas.service.RentMovieService;
import com.joanadantas.service.ReturnMovieService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {

    @Bean
    public RentMovieService getRentMovieService() {
        return new RentMovieService();
    }

    @Bean
    public ReturnMovieService getReturnMovieService() {
        return new ReturnMovieService();
    }
}

