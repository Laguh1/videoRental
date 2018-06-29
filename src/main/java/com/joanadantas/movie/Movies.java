package com.joanadantas.movie;

import com.joanadantas.movie.dao.MoviesCatalogueLoader;

import java.util.HashMap;

public class Movies {

    public HashMap<String, Movie> getMovies() {
        return movies;
    }

    HashMap<String, Movie> movies;

    public Movies(){
        this.movies = MoviesCatalogueLoader.getAllMoviesMap();
    }
}
