package com.joanadantas;

import com.joanadantas.service.ReturnMovieService;
import com.joanadantas.service.ReturnService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ContextConfiguration {

    @Bean
    public ReturnService returnService() {
        ReturnService returnMovieService = new ReturnMovieService();
        return returnMovieService;
    }
}
