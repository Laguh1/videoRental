package com.joanadantas;

import com.joanadantas.service.RentMovieService;
import com.joanadantas.service.RentService;
import com.joanadantas.service.ReturnMovieService;
import com.joanadantas.service.ReturnService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;


@Configuration
@EnableWebMvc
@ComponentScan(basePackages ={"com.joanadantas"}
, excludeFilters = {
        @ComponentScan.Filter(type = FilterType.ANNOTATION, value = Configuration.class)
}
)
public class SpringConfig extends WebMvcConfigurerAdapter {

    @Bean
    public RentService getRentMovieService() {
        return new RentMovieService();
    }

    @Bean
    public ReturnService getReturnMovieService() {
        return new ReturnMovieService();
    }
}

