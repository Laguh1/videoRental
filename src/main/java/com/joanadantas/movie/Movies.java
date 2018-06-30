package com.joanadantas.movie;

import com.joanadantas.movie.dao.MoviesCatalogueLoader;
import java.util.List;

public class Movies {

    List<Movie> movies;

    public List<Movie> getMovies() {
        return movies;
    }

    public Movies(){
        this.movies = MoviesCatalogueLoader.getAllMoviesList();
    }
}
