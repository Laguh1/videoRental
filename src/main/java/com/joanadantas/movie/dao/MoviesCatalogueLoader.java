package com.joanadantas.movie.dao;

import com.joanadantas.movie.Movie;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class MoviesCatalogueLoader {

    //Creates a cached list of films for testing the app

    private static List<Movie> allMovies;
    private static HashMap<String, Movie> allMoviesMap;


    public static List<Movie> getAllMoviesList(){
        if (allMovies != null){
            return allMovies;
        }else{
            allMovies = new ArrayList<>();
            Movie movie1 = new Movie("Matrix 11", LocalDate.of(2018,6, 13), "1.50");
            Movie movie2 = new Movie("Spider Man", LocalDate.of(2017,6,13), "1.50");
            Movie movie3 = new Movie("Spider Man 2", LocalDate.of(2016,6,13), "1.50");
            Movie movie4 = new Movie("Out of Africa", LocalDate.of(2011,6,13), "1.50");
            allMovies.addAll(Arrays.asList(movie1, movie2, movie3, movie4));
            return allMovies;
        }
    }

    public static HashMap<String, Movie> getAllMoviesMap(){
        if (allMoviesMap != null){
            return allMoviesMap;
        }else{
            allMoviesMap = new HashMap<>();
            getAllMoviesList();
            for(int index = 1; index<getAllMoviesList().size()+1; index++){
                allMoviesMap.put(String.format("%03d", index),getAllMoviesList().get(index-1));

            }
            return allMoviesMap;
        }
    }
}
