package com.joanadantas.movie.dao;

import com.joanadantas.movie.Movie;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MoviesCatalogueLoader {

    //Creates a cached list of films for testing the app

    private static List<Movie> allMovies = new ArrayList<>();

    public static List<Movie> getAllMovies(){
        if (allMovies != null){
            return allMovies;
        }else{
            Movie movie1 = new Movie("Matrix 11", LocalDate.of(2018,6, 13), "1.50");
            Movie movie2 = new Movie("Spider Man", LocalDate.of(2017,6,13), "1.50");
            Movie movie3 = new Movie("Spider Man 2", LocalDate.of(2016,6,13), "1.50");
            Movie movie4 = new Movie("Out of Africa", LocalDate.of(2011,6,13), "1.50");
            allMovies.addAll(Arrays.asList(movie1, movie2, movie3, movie4));
            return allMovies;
        }
    }
}
