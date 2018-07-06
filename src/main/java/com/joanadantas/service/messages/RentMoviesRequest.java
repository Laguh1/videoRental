package com.joanadantas.service.messages;

public class RentMoviesRequest {

    private String movieId;
    private int numberOfDays;

    public String getMovieId() {
        return movieId;
    }

    private RentMoviesRequest(){
    }

    public void setMovieId(String movieId) {
        this.movieId = movieId;
    }

    public int getNumberOfDays() {
        return numberOfDays;
    }

    public void setNumberOfDays(int numberOfDays) {
        this.numberOfDays = numberOfDays;
    }
}

