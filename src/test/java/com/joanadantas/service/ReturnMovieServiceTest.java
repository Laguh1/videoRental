package com.joanadantas.service;

import com.joanadantas.customer.Customer;
import com.joanadantas.customer.dao.CustomersLoader;
import com.joanadantas.movie.Movie;
import com.joanadantas.movie.dao.MoviesCatalogueLoader;
import org.junit.*;
import org.junit.rules.ExpectedException;

import java.time.LocalDate;

import static org.junit.Assert.*;

public class ReturnMovieServiceTest {

    private static final String CUSTOMER_ID = "001";
    private static final String NON_EXISTANT_CUSTOMER_ID = "999";
    private static final String NOT_RENTED_MOVIE_ID = "001";
    private static final String MOVIE_ID = "002";
    private static final String NON_EXISTANT_MOVIE_ID = "999";
    private static final int NUMBER_OF_DAYS_TO_RENT = 1;

    private ReturnMovieService objectUnderTest = new ReturnMovieService();

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void returnAMovie_WhenCustomerDoesNotExist_ShouldThrowException() throws CustomException{

        thrown.expect(CustomException.class);
        thrown.expectMessage("Customer with id: "+NON_EXISTANT_CUSTOMER_ID+" not found.");

        objectUnderTest.returnAMovie(NON_EXISTANT_CUSTOMER_ID, MOVIE_ID);
    }

    @Test
    public void returnAMovie_WhenMovieDoesNotExist_ShouldThrowException() throws CustomException{

        thrown.expect(CustomException.class);
        thrown.expectMessage("Movie with id: "+NON_EXISTANT_MOVIE_ID+" not found.");

        objectUnderTest.returnAMovie(CUSTOMER_ID, NON_EXISTANT_MOVIE_ID);
    }

    @Test
    public void returnAMovie_WhenMovieIsNotRented_ShouldThrowException() throws CustomException{
        Movie movie = MoviesCatalogueLoader.getAllMoviesMap().get(NOT_RENTED_MOVIE_ID);

        thrown.expect(CustomException.class);
        thrown.expectMessage("Movie "+ movie.getTitle()+" is currently not rented by this customer.");

        objectUnderTest.returnAMovie(CUSTOMER_ID, NOT_RENTED_MOVIE_ID);
    }

    @Test
    public void returnAMovie_WhenReturnMovieIsSuccesfull_ShouldSetValuesCorrectly() throws CustomException{
        RentMovieService rentMovieService = new RentMovieService();
        rentMovieService.rentAMovie(CUSTOMER_ID, MOVIE_ID, NUMBER_OF_DAYS_TO_RENT);

        Movie movie = MoviesCatalogueLoader.getAllMoviesMap().get(MOVIE_ID);
        Customer customer = CustomersLoader.getAllCustomersMap().get(CUSTOMER_ID);

        objectUnderTest.returnAMovie(CUSTOMER_ID, MOVIE_ID);

        assertTrue(movie.getIsAvailable());
        assertEquals("", movie.getReturnDate());
        assertFalse(customer.getMoviesRented().contains(movie));
    }
}