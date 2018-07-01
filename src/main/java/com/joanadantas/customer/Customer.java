package com.joanadantas.customer;

import com.joanadantas.movie.Movie;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Customer {

    private final String id;
    private final String name;
    private int bonusPoint;
    private Map<String, Integer> amountPaidPerMovie;
    private List<Movie> moviesRented;

    public Customer(String id, String name){
        this.id = id;
        this.name = name;
        this.bonusPoint = 0;
        this.amountPaidPerMovie = new HashMap<>();
        this.moviesRented = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getBonusPoint() {
        return bonusPoint;
    }

    public Map<String, Integer> getAmountPaidPerMovie() {
        return amountPaidPerMovie;
    }

    public List<Movie> getMoviesRented(){
        return moviesRented;
    }

    public void setBonusPoint(int bonusPoint) {
        this.bonusPoint = bonusPoint;
    }

    public void insertMovieIntoRentedList(Movie movie){
        moviesRented.add(movie);
    }

    public void removeMovieFromRentedList(Movie movie){
        moviesRented.remove(movie);
    }

    public void setAmountPaidPerMovie(String movieId, int amount){
        amountPaidPerMovie.put(movieId, amount);
    }

    public void removeAmountPaidPerMovie(String movieId){
        amountPaidPerMovie.remove(movieId);
    }
}
