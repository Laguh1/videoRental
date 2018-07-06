package com.joanadantas.customer;

import com.joanadantas.movie.Movie;

import java.util.List;
import java.util.Map;

public class Customer {

    private String id;
    private String name;
    private int bonusPoint;
    private Map<String, Integer> amountPaidPerMovie;
    private List<Movie> moviesRented;

    private Customer(){}

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
