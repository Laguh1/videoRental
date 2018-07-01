package com.joanadantas.service;

import com.joanadantas.customer.Customer;
import com.joanadantas.customer.dao.CustomersLoader;
import com.joanadantas.movie.Movie;
import com.joanadantas.movie.dao.MoviesCatalogueLoader;
import org.junit.*;
import org.junit.rules.ExpectedException;

import java.time.LocalDate;

import static org.junit.Assert.*;

public class RentMovieServiceTest {

    private static final String CUSTOMER_ID = "001";
    private static final String NON_EXISTANT_CUSTOMER_ID = "999";
    private static final String UNAVAILABLE_MOVIE_ID = "001";
    private static final String MOVIE_ID = "002";
    private static final String NON_EXISTANT_MOVIE_ID = "999";
    private static final int REGULAR_BONUS_POINT = 1;
    private static final int NUMBER_OF_DAYS_TO_RENT = 2;

    private RentMovieService objectUnderTest = new RentMovieService();

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void rentAMovie_WhenCustomerDoesNotExist_ShouldThrowException() throws CustomException{

        thrown.expect(CustomException.class);
        thrown.expectMessage("Customer with id: "+NON_EXISTANT_CUSTOMER_ID+" not found.");

        objectUnderTest.rentAMovie(NON_EXISTANT_CUSTOMER_ID, MOVIE_ID, NUMBER_OF_DAYS_TO_RENT);
    }

    @Test
    public void rentAMovie_WhenMovieDoesNotExist_ShouldThrowException() throws CustomException{

        thrown.expect(CustomException.class);
        thrown.expectMessage("Movie with id: "+NON_EXISTANT_MOVIE_ID+" not found.");

        objectUnderTest.rentAMovie(CUSTOMER_ID, NON_EXISTANT_MOVIE_ID, NUMBER_OF_DAYS_TO_RENT);
    }

    @Test
    public void rentAMovie_WhenMovieIsNotAvailable_ShouldThrowException() throws CustomException{
        Movie movie = MoviesCatalogueLoader.getAllMoviesMap().get(UNAVAILABLE_MOVIE_ID);
        objectUnderTest.rentAMovie(CUSTOMER_ID, UNAVAILABLE_MOVIE_ID, NUMBER_OF_DAYS_TO_RENT);

        thrown.expect(CustomException.class);
        thrown.expectMessage("Movie "+ movie.getTitle()+" is not Available. It will be returned on: "+movie.getReturnDate());

        objectUnderTest.rentAMovie(CUSTOMER_ID, UNAVAILABLE_MOVIE_ID, NUMBER_OF_DAYS_TO_RENT);

        ReturnMovieService returnMovieService = new ReturnMovieService();
        returnMovieService.returnAMovie(CUSTOMER_ID,UNAVAILABLE_MOVIE_ID);
    }

    @Test
    public void rentAMovie_WhenRentMovieIsSuccesfull_ShouldSetValuesCorrectly() throws CustomException{
        Movie movie = MoviesCatalogueLoader.getAllMoviesMap().get(MOVIE_ID);
        Customer customer = CustomersLoader.getAllCustomersMap().get(CUSTOMER_ID);
        int currentCustomerBonusPoint = customer.getBonusPoint();

        objectUnderTest.rentAMovie(CUSTOMER_ID, MOVIE_ID, NUMBER_OF_DAYS_TO_RENT);

        assertEquals(currentCustomerBonusPoint+REGULAR_BONUS_POINT, customer.getBonusPoint());
        assertFalse(movie.getIsAvailable());
        assertEquals(LocalDate.now().plusDays(NUMBER_OF_DAYS_TO_RENT).toString(), movie.getReturnDate());
        assertTrue(customer.getMoviesRented().contains(movie));
        assertEquals((Integer)movie.getPricing().calculateRentPrice(NUMBER_OF_DAYS_TO_RENT), customer.getAmountPaidPerMovie().get(MOVIE_ID));

        ReturnMovieService returnMovieService = new ReturnMovieService();
        returnMovieService.returnAMovie(CUSTOMER_ID, MOVIE_ID);
    }
}