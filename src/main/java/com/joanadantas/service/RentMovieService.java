package com.joanadantas.service;

import com.joanadantas.customer.Customer;
import com.joanadantas.movie.Movie;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class RentMovieService {

    public void rentAMovie(Customer customer, Movie movie, int numberOfDaysToRent) throws CustomException{

        if(!movie.getIsAvailable()){
            throw new CustomException("Movie "+movie.getTitle()+" is not Available. It will be returned on: "+movie.getReturnDate());
        }

        customer.setBonusPoint(getBonusPoints(customer, movie));
        movie.setIsAvailable(false);
        String returnDate = LocalDate.now().plusDays(numberOfDaysToRent).toString();
        movie.setReturnDate(returnDate);
        customer.insertMovieIntoRentedList(movie);
        customer.setAmountPaidPerMovie(movie, numberOfDaysToRent);

    }

    private static int getBonusPoints(Customer customer, Movie movie) {
        String moviePricingRate = movie.getPricing().getPricingRate();
        int customerCurrentBonusPoints = customer.getBonusPoint();
        int pointToAdd = moviePricingRate.equals("New_Release")? 2:1;
        return customerCurrentBonusPoints+pointToAdd;
    }
}
