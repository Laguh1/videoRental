package com.joanadantas.movie;

import com.joanadantas.movie.dao.MoviesCatalogueLoader;

import java.util.HashMap;

public class Movies {

    HashMap<String, Movie> movies;

    public HashMap<String, Movie> getMovies() {
        return movies;
    }

    public Movies(){
        this.movies = MoviesCatalogueLoader.getAllMoviesMap();
    }
}
