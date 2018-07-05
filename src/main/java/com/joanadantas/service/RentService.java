package com.joanadantas.service;

import com.joanadantas.service.messages.SuccessfulRentMessage;


public interface RentService {

    SuccessfulRentMessage rentAMovie(String customerId, String movieId, int numberOfDaysToRent) throws CustomException;
}
