package com.joanadantas.service;

import com.joanadantas.customer.Customer;
import com.joanadantas.movie.Movie;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class ReturnMovieService {

    public int returnAMovie(Customer customer, Movie movie) throws CustomException{
        LocalDate actualReturnDate = LocalDate.now();
        LocalDate returnDate = LocalDate.parse(movie.getReturnDate());
        int daysDue = actualReturnDate.getDayOfYear()-returnDate.getDayOfYear();

        int amountDueToPay = daysDue*movie.getPricing().getPricePerDay();
        movie.setIsAvailable(true);
        movie.setReturnDate("");
        customer.removeMovieFromRentedList(movie);
        customer.setAmountPaidPerMovie(movie, daysDue);

        return amountDueToPay;

    }
}
