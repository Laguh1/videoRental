package com.joanadantas.service;

import com.joanadantas.customer.Customer;
import com.joanadantas.customer.dao.CustomersLoader;
import com.joanadantas.movie.Movie;
import com.joanadantas.movie.dao.MoviesCatalogueLoader;
import com.joanadantas.service.rental.RentMovieService;
import com.joanadantas.service.rental.RentService;
import com.joanadantas.service.rental.ReturnMovieService;
import com.joanadantas.service.rental.ReturnService;
import org.junit.*;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.*;

public class ReturnMovieServiceTest {

    private static final String CUSTOMER_ID = "MYS";
    private static final String NON_EXISTANT_CUSTOMER_ID = "999";
    private static final String NOT_RENTED_MOVIE_ID = "001";
    private static final String MOVIE_ID = "002";
    private static final String NON_EXISTANT_MOVIE_ID = "999";
    private static final int NUMBER_OF_DAYS_TO_RENT = 1;

    private Movie notRentedMovie;
    private Movie rentedMovie;
    private RentService rentMovieService;
    private ReturnService objectUnderTest;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Before
    public void setUp() throws CustomException{
        objectUnderTest = ReturnMovieService.getInstance();
        notRentedMovie = MoviesCatalogueLoader.getAllMoviesMap().get(NOT_RENTED_MOVIE_ID);
        if(!notRentedMovie.getIsAvailable()){
            objectUnderTest.returnAMovie(CUSTOMER_ID, NOT_RENTED_MOVIE_ID);
        }
        rentedMovie = MoviesCatalogueLoader.getAllMoviesMap().get(MOVIE_ID);
        if(rentedMovie.getIsAvailable()){
            rentMovieService = RentMovieService.getInstance();
            rentMovieService.rentAMovie(CUSTOMER_ID, MOVIE_ID, NUMBER_OF_DAYS_TO_RENT);
        }
    }

    @After
    public void clear() throws CustomException{
        if(!rentedMovie.getIsAvailable()){
            objectUnderTest.returnAMovie(CUSTOMER_ID, MOVIE_ID);
        }
    }

    @Test
    public void returnAMovie_WhenCustomerDoesNotExist_ShouldThrowException() throws CustomException{
        thrown.expect(CustomException.class);
        thrown.expectMessage(String.format("Customer with id: %s not found.", NON_EXISTANT_CUSTOMER_ID));

        objectUnderTest.returnAMovie(NON_EXISTANT_CUSTOMER_ID, MOVIE_ID);
    }

    @Test
    public void returnAMovie_WhenMovieDoesNotExist_ShouldThrowException() throws CustomException{
        thrown.expect(CustomException.class);
        thrown.expectMessage(String.format("Movie with id: %s not found.", NON_EXISTANT_MOVIE_ID));

        objectUnderTest.returnAMovie(CUSTOMER_ID, NON_EXISTANT_MOVIE_ID);
    }

    @Test
    public void returnAMovie_WhenMovieIsNotRented_ShouldThrowException() throws CustomException{
        Movie movie = MoviesCatalogueLoader.getAllMoviesMap().get(NOT_RENTED_MOVIE_ID);

        thrown.expect(CustomException.class);
        thrown.expectMessage(String.format("Return operation failed for Movie %s. Movie %s is currently not rented by this customer.", movie.getTitle(), movie.getTitle()));

        objectUnderTest.returnAMovie(CUSTOMER_ID, NOT_RENTED_MOVIE_ID);
    }

    @Test
    public void returnAMovie_WhenReturnMovieIsSuccesfull_ShouldSetValuesCorrectly() throws CustomException{
        Movie movie = MoviesCatalogueLoader.getAllMoviesMap().get(MOVIE_ID);
        Customer customer = CustomersLoader.getAllCustomersMap().get(CUSTOMER_ID);

        objectUnderTest.returnAMovie(CUSTOMER_ID, MOVIE_ID);

        assertTrue(movie.getIsAvailable());
        assertEquals("", movie.getReturnDate());
        assertFalse(customer.getMoviesRented().contains(movie));
    }
}