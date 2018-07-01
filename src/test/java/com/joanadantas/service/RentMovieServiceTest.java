package com.joanadantas.service;

import com.joanadantas.customer.Customer;
import com.joanadantas.movie.Movie;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.time.LocalDate;

import static org.junit.Assert.*;

public class RentMovieServiceTest {

    private static final String CUSTOMER_ID = "001";
    private static final String CUSTOMER_NAME = "Mary Smith";
    private static final String MOVIE_ID = "001";
    private static final String MOVIE_TITLE = "Any Movie Title";
    private static final String MOVIE_PUBLISH_DATE = "2017-06-16";
    private static final int REGULAR_BONUS_POINT = 1;
    private static final int NUMBER_OF_DAYS_TO_RENT = 2;

    private Customer mockCustomer = new Customer(CUSTOMER_ID, CUSTOMER_NAME);
    private Movie mockMovie = new Movie(MOVIE_ID, MOVIE_TITLE, MOVIE_PUBLISH_DATE);

    private RentMovieService objectUnderTest = new RentMovieService();

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @After
    public void clear(){
        mockMovie.setIsAvailable(true);
    }

    @Test
    public void rentAMovie_WhenMovieIsNotAvailable_ShouldThrowException() throws CustomException{
        mockMovie.setIsAvailable(false);

        thrown.expect(CustomException.class);
        thrown.expectMessage("Movie "+mockMovie.getTitle()+" is not Available. It will be returned on: "+mockMovie.getReturnDate());

        objectUnderTest.rentAMovie(mockCustomer, mockMovie, NUMBER_OF_DAYS_TO_RENT);
    }

    @Test
    public void rentAMovie_WhenRentRegularMovie_ShouldAdd1BonusPointToCustomer() throws CustomException{

        int currentCustomerBonusPoint = mockCustomer.getBonusPoint();
        objectUnderTest.rentAMovie(mockCustomer, mockMovie, NUMBER_OF_DAYS_TO_RENT);

        assertEquals(currentCustomerBonusPoint+REGULAR_BONUS_POINT, mockCustomer.getBonusPoint());
    }

    /*
     if(!movie.getIsAvailable()){
        throw new CustomException("Movie "+movie.getTitle()+" is not Available. It will be returned on: "+movie.getReturnDate());
    }

        customer.setBonusPoint(getBonusPoints(customer, movie));
        movie.setIsAvailable(false);
    String returnDate = LocalDate.now().plusDays(numberOfDaysToRent).toString();
        movie.setReturnDate(returnDate);
        customer.insertMovieIntoRentedList(movie);
        customer.setAmountPaidPerMovie(movie, numberOfDaysToRent);
*/
}