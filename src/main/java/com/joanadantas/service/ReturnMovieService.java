package com.joanadantas.service;

import com.joanadantas.customer.Customer;
import com.joanadantas.customer.dao.CustomersLoader;
import com.joanadantas.movie.Movie;
import com.joanadantas.movie.dao.MoviesCatalogueLoader;
import com.joanadantas.service.messages.SuccessfulReturnMessage;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class ReturnMovieService implements ReturnService{

    private static final String CUSTOMER_NOT_FOUND = "Customer with id: %s not found.";
    private static final String MOVIE_NOT_FOUND = "Movie with id: %s not found.";
    private static final String MOVIE_NOT_RENTED_BYCUSTOMER = "Movie %s is currently not rented by this customer.";

    public SuccessfulReturnMessage returnAMovie(String customerId, String movieId) throws CustomException{

        Customer customer = CustomersLoader.getAllCustomersMap().get(customerId);

        if (customer == null){
            throw new CustomException(String.format(CUSTOMER_NOT_FOUND, customerId));
        }

        Movie movie = MoviesCatalogueLoader.getAllMoviesMap().get(movieId);

        if (movie == null){
            throw new CustomException(String.format(MOVIE_NOT_FOUND, movieId));
        }

        if(movie.getIsAvailable() || !customer.getMoviesRented().contains(movie)){
            throw new CustomException(String.format(MOVIE_NOT_RENTED_BYCUSTOMER, movie.getTitle()));
        }
        int amountDueToPay = getAmountDueToPay(movie, customer);
        movie.setIsAvailable(true);
        movie.setReturnDate("");
        customer.removeMovieFromRentedList(movie);
        customer.removeAmountPaidPerMovie(movieId);
        return new SuccessfulReturnMessage(customerId, customer.getName(), movie, amountDueToPay);
    }

    private int getAmountDueToPay(Movie movie, Customer customer) {
        LocalDate actualReturnDate = LocalDate.now();
        LocalDate rentDate = LocalDate.parse(movie.getRentDate());
        int actualDaysRented = actualReturnDate.getDayOfYear()-rentDate.getDayOfYear();
        int totalAmount = movie.getPricing().calculateRentPrice(actualDaysRented);
        int amountAlreadyPaid = customer.getAmountPaidPerMovie().get(movie.getId());
        return totalAmount>amountAlreadyPaid?totalAmount-amountAlreadyPaid:0;
    }
}
