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
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDate;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class RentMovieServiceTest {

    @Configuration
    static class ContextConfiguration {

        @Bean
        public ReturnService returnService() {
            ReturnService returnMovieService = ReturnMovieService.getInstance();
            return returnMovieService;
        }
    }

    private static final String CUSTOMER_ID = "MYS";
    private static final String NON_EXISTANT_CUSTOMER_ID = "999";
    private static final String UNAVAILABLE_MOVIE_ID = "001";
    private static final String MOVIE_ID = "002";
    private static final String NON_EXISTANT_MOVIE_ID = "999";
    private static final int REGULAR_BONUS_POINT = 1;
    private static final int NUMBER_OF_DAYS_TO_RENT = 2;

    private Movie unavailableMovie;
    private Movie notRentedMovie;
    @Autowired
    private ReturnService returnMovieService;
    private RentService objectUnderTest;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Before
    public void setUp() throws CustomException{
        objectUnderTest = RentMovieService.getInstance();
        unavailableMovie = MoviesCatalogueLoader.getAllMoviesMap().get(UNAVAILABLE_MOVIE_ID);
        if(unavailableMovie.getIsAvailable()){
            objectUnderTest.rentAMovie(CUSTOMER_ID, UNAVAILABLE_MOVIE_ID, NUMBER_OF_DAYS_TO_RENT);
        }
        notRentedMovie = MoviesCatalogueLoader.getAllMoviesMap().get(MOVIE_ID);
        if(!notRentedMovie.getIsAvailable()){
            returnMovieService.returnAMovie(CUSTOMER_ID, MOVIE_ID);
        }
    }

    @After
    public void clear() throws CustomException{
        if(!unavailableMovie.getIsAvailable()){
            returnMovieService.returnAMovie(CUSTOMER_ID, UNAVAILABLE_MOVIE_ID);
        }
        if(!notRentedMovie.getIsAvailable()){
            returnMovieService.returnAMovie(CUSTOMER_ID, MOVIE_ID);
        }
    }

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

        thrown.expect(CustomException.class);
        thrown.expectMessage("Movie "+ movie.getTitle()+" is not Available. It will be returned on: "+movie.getReturnDate());

        objectUnderTest.rentAMovie(CUSTOMER_ID, UNAVAILABLE_MOVIE_ID, NUMBER_OF_DAYS_TO_RENT);

       // ReturnMovieService returnMovieService = new ReturnMovieService();
       // returnMovieService.returnAMovie(CUSTOMER_ID,UNAVAILABLE_MOVIE_ID);
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
        assertEquals(LocalDate.now().toString(), movie.getRentDate());
        assertTrue(customer.getMoviesRented().contains(movie));
        assertEquals((Integer)movie.getPricing().calculateRentPrice(NUMBER_OF_DAYS_TO_RENT), customer.getAmountPaidPerMovie().get(MOVIE_ID));

      //  ReturnMovieService returnMovieService = new ReturnMovieService();
      //  returnMovieService.returnAMovie(CUSTOMER_ID, MOVIE_ID);
    }
}