package com.joanadantas.movie.dao;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.joanadantas.movie.Movie;

import java.io.File;
import java.util.HashMap;
import java.util.List;

public class MoviesCatalogueLoader {

    //Creates a cached list of films for using the app simulates database as json file
    private static List<Movie> allMovies;
    private static HashMap<String, Movie> allMoviesMap;

    public static List<Movie> getAllMoviesList(){
        if (allMovies != null){
            return allMovies;
        }else{
            allMovies = new MoviesCatalogueLoader().getMoviesFromJson();
            allMovies.stream().forEach(movie -> movie.setPricing());
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

    private List<Movie> getMoviesFromJson(){
        ClassLoader classLoader = getClass().getClassLoader();
        File jsonFile = new File(classLoader.getResource("moviesCatalogue.json").getFile());
        ObjectMapper objectMapper = new ObjectMapper();
        TypeReference<List<Movie>> mapType = new TypeReference<List<Movie>>() {};
        List<Movie> moviesList = null;
        try{
            moviesList = objectMapper.readValue(jsonFile, mapType);
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return moviesList;
    }
}
