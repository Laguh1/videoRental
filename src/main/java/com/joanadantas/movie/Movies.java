package com.joanadantas.movie;

import com.joanadantas.movie.dao.MoviesCatalogueLoader;
import java.util.List;

public final class Movies {

    private final List<Movie> moviesList;
    private static Movies movies = null;

    public List<Movie> getMovies() {
        return moviesList;
    }

    private Movies(){
        this.moviesList = MoviesCatalogueLoader.getAllMoviesList();
    }


    public static Movies getInstance(){
        if(movies!=null){
            return movies;
        }else{
            return new Movies();
        }
    }
}
