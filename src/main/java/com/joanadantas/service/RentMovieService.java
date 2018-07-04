package com.joanadantas.service;

import com.joanadantas.customer.Customer;
import com.joanadantas.customer.dao.CustomersLoader;
import com.joanadantas.movie.Movie;
import com.joanadantas.movie.dao.MoviesCatalogueLoader;
import com.joanadantas.service.messages.SuccessfulRentMessage;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class RentMovieService implements RentService{

    private static final String CUSTOMER_NOT_FOUND = "Customer with id: %s not found.";
    private static final String MOVIE_NOT_FOUND = "Movie with id: %s not found.";
    private static final String MOVIE_NOT_AVAILABLE = "Movie %s is not Available. It will be returned on: %s";

    public SuccessfulRentMessage rentAMovie(String customerId, String movieId, int numberOfDaysToRent) throws CustomException{

        Customer customer = CustomersLoader.getAllCustomersMap().get(customerId);

        if (customer == null){
            throw new CustomException(String.format(CUSTOMER_NOT_FOUND, customerId));
        }

        Movie movie = MoviesCatalogueLoader.getAllMoviesMap().get(movieId);

        if (movie == null){
            throw new CustomException(String.format(MOVIE_NOT_FOUND, movieId));
        }

        if(!movie.getIsAvailable()){
            throw new CustomException(String.format(MOVIE_NOT_AVAILABLE, movie.getTitle(), movie.getReturnDate()));
        }

        customer.setBonusPoint(getBonusPoints(customer, movie));
        movie.setIsAvailable(false);
        String returnDate = LocalDate.now().plusDays(numberOfDaysToRent).toString();
        movie.setReturnDate(returnDate);
        String rentDate = LocalDate.now().toString();
        movie.setRentDate(rentDate);
        customer.insertMovieIntoRentedList(movie);
        int amountToPay = movie.getPricing().calculateRentPrice(numberOfDaysToRent);
        customer.setAmountPaidPerMovie(movieId, amountToPay);

        return new SuccessfulRentMessage(customerId, customer.getName(), movie, customer.getAmountPaidPerMovie().get(movie.getId()));

    }

    private int getBonusPoints(Customer customer, Movie movie) {
        String moviePricingRate = movie.getPricing().getPricingRate();
        int customerCurrentBonusPoints = customer.getBonusPoint();
        int pointToAdd = moviePricingRate.equals("New_Release")? 2:1;
        return customerCurrentBonusPoints+pointToAdd;
    }
}
