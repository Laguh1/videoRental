package com.joanadantas;

import com.joanadantas.service.RentMovieService;
import com.joanadantas.service.RentService;
import com.joanadantas.service.ReturnMovieService;
import com.joanadantas.service.ReturnService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;


@Configuration
@EnableWebMvc
@ComponentScan(basePackages ={"com.joanadantas"}

)
public class SpringConfig {

    @Bean
    public RentService getRentMovieService() {
        return new RentMovieService();
    }

    @Bean
    public ReturnService getReturnMovieService() {
        return new ReturnMovieService();
    }
}

