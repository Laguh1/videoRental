package com.joanadantas.service.rental;

import com.joanadantas.service.CustomException;
import com.joanadantas.service.messages.SuccessfulRentMessage;


public interface RentService {

    SuccessfulRentMessage rentAMovie(String customerId, String movieId, int numberOfDaysToRent) throws CustomException;
}
