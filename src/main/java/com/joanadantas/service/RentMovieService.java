package com.joanadantas.service;

import com.joanadantas.customer.Customer;
import com.joanadantas.customer.dao.CustomersLoader;
import com.joanadantas.movie.Movie;
import com.joanadantas.movie.dao.MoviesCatalogueLoader;
import com.joanadantas.service.dto.SuccesfullRentMessage;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class RentMovieService {

    public SuccesfullRentMessage rentAMovie(String customerId, String movieId, int numberOfDaysToRent) throws CustomException{


        Customer customer = CustomersLoader.getAllCustomersMap().get(customerId);

        if (customer == null){
            throw new CustomException("Customer with id: "+customerId+" not found.");
        }

        Movie movie = MoviesCatalogueLoader.getAllMoviesMap().get(movieId);

        if (movie == null){
            throw new CustomException("Movie with id: "+movieId+" not found.");
        }

        if(!movie.getIsAvailable()){
            throw new CustomException("Movie "+movie.getTitle()+" is not Available. It will be returned on: "+movie.getReturnDate());
        }

        customer.setBonusPoint(getBonusPoints(customer, movie));
        movie.setIsAvailable(false);
        String returnDate = LocalDate.now().plusDays(numberOfDaysToRent).toString();
        movie.setReturnDate(returnDate);
        customer.insertMovieIntoRentedList(movie);
        customer.setAmountPaidPerMovie(movie, numberOfDaysToRent);

        return new SuccesfullRentMessage(customerId, customer.getName(), movie, "Succesfull", customer.getAmountPaidPerMovie().get(movie));

    }

    private static int getBonusPoints(Customer customer, Movie movie) {
        String moviePricingRate = movie.getPricing().getPricingRate();
        int customerCurrentBonusPoints = customer.getBonusPoint();
        int pointToAdd = moviePricingRate.equals("New_Release")? 2:1;
        return customerCurrentBonusPoints+pointToAdd;
    }
}
