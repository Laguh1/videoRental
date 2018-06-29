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
            Movie movie1 = new Movie("001","Matrix 11", "2018-06-16");
            Movie movie2 = new Movie("002","Spider Man", "2017-06-16");
            Movie movie3 = new Movie("003","Spider Man 2", "2016-06-16");
            Movie movie4 = new Movie("004","Out of Africa", "2011-06-16");
            allMovies.addAll(Arrays.asList(movie1, movie2, movie3, movie4));
            return allMovies;
        }
    }

    public static HashMap<String, Movie> getAllMoviesMap(){
        if (allMoviesMap != null){
            return allMoviesMap;
        }else{
            allMoviesMap = new HashMap<>();
            getAllMoviesList().forEach(movie -> allMoviesMap.put(movie.getId(),movie));
            return allMoviesMap;
        }
    }
}
